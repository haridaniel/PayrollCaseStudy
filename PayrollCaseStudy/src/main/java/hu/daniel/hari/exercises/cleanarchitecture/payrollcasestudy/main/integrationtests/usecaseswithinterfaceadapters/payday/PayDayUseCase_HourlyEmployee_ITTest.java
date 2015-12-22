package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.PaydayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;

public class PayDayUseCase_HourlyEmployee_ITTest extends AbstractUseCaseITTest {
	private static final double OVERTIME_WAGE_MULTIPLIER = 1.5d;
	
	private static final LocalDate LAST_FRIDAY = LocalDate.of(2015, 11, 27);	//in previous period
	private static final LocalDate LAST_SATURDAY = LocalDate.of(2015, 11, 28);	//this period start
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);	//this period end
	private static final LocalDate THIS_SATURDAY = LocalDate.of(2015, 12, 05);	//in next period

	private final int employeeId = 1;
	private final int hourlyWage = 10;
	
	private abstract class Case {
		LocalDate payDate;
		List<AddTimeCardRequest> timeCards;
		int thenPayCheckNetAmountSum;
	}
	
	private class NoTimeCard_ThenZeroAmountPayCheck extends Case {{
		payDate = THIS_FRIDAY;
		timeCards = Collections.emptyList();
		thenPayCheckNetAmountSum = 0;
	}}
	
	private class OneTimeCard extends Case {{
		payDate = THIS_FRIDAY;
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 8));
		}};
		thenPayCheckNetAmountSum = hourlyWage * (8);
	}}

	private class TwoTimeCards_AtBordersOfPayPeriod extends Case {{
		payDate = THIS_FRIDAY;
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, LAST_SATURDAY, 4));
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 8));
		}};
		thenPayCheckNetAmountSum = hourlyWage * (4 + 8);
	}}

	private class ThreeTimeCards_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods extends Case {{
		payDate = THIS_FRIDAY;
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, LAST_FRIDAY, 3)); 	//In previous pay period, should be IGNORED
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 5)); 	//In this pay period
			add(new AddTimeCardRequest(employeeId, THIS_SATURDAY, 7)); 	//In next pay period, should be IGNORED
		}};
		thenPayCheckNetAmountSum = hourlyWage * (5);
	}}
	
	private class OneTimeCard_OverTimeWorkingHour extends Case {{
		payDate = THIS_FRIDAY;
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 12));
		}};
		int normalWage = hourlyWage * (8);
		int overTimeWage = (int) (hourlyWage * OVERTIME_WAGE_MULTIPLIER * (4));
		thenPayCheckNetAmountSum = normalWage + overTimeWage;
	}}
	
	public PayDayUseCase_HourlyEmployee_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPaySingleHourlyEmployee_noTimeCard_ZeroAmountPayCheck() throws Exception {
		givenWhenThen(new NoTimeCard_ThenZeroAmountPayCheck());
	}

	@Test
	public void testPaySingleHourlyEmployee_oneTimeCard() throws Exception {
		givenWhenThen(new OneTimeCard());
	}

	@Test
	public void testPaySingleHourlyEmployee_TwoTimeCards_AtBordersOfPaymentPeriod() throws Exception {
		givenWhenThen(new TwoTimeCards_AtBordersOfPayPeriod());
	}
	
	@Test
	public void testPaySingleHourlyEmployee_ThreeTimeCards_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods() throws Exception {
		givenWhenThen(new ThreeTimeCards_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods());
	}
	
	@Test
	public void testPaySingleHourlyEmployee_OneTimeCard_OvertimeWorkingHour() throws Exception {
		givenWhenThen(new OneTimeCard_OverTimeWorkingHour());
	}
	
	private void givenWhenThen(Case theCase) {
		givenAHourlyEmployee();
		givenTimeCards(theCase.timeCards);
		
		Collection<PayCheck> payChecks = whenPayDayUseCaseExecuted(theCase.payDate);
		
		thenPayChecksNetAmountSumShouldBe(payChecks, theCase.thenPayCheckNetAmountSum);
	}

	private void givenAHourlyEmployee() {
		useCaseFactory.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, "", "", hourlyWage));
	}

	private void givenTimeCards(List<AddTimeCardRequest> addTimeCardRequests) {
		for (AddTimeCardRequest addTimeCardRequest : addTimeCardRequests) {
			useCaseFactory.addTimeCardUseCase().execute(addTimeCardRequest);
		}
	}

	private Collection<PayCheck> whenPayDayUseCaseExecuted(LocalDate payDate) {
		PaydayUseCase paydayUseCase = useCaseFactory.paydayUseCase();
		paydayUseCase.execute(new PaydayRequest(payDate));
		return paydayUseCase.getPayChecks();
	}

	private void thenPayChecksNetAmountSumShouldBe(Collection<PayCheck> payChecks, int netAmount) {
		int sumNetAmount = payChecks.stream()
			.mapToInt(payCheck -> payCheck.getNetAmount())
			.sum();
		assertThat(sumNetAmount, is(netAmount));
	}

}
