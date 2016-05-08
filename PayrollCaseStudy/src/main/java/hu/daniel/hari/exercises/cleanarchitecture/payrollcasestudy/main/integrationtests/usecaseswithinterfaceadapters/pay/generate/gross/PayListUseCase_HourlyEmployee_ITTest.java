package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate.gross;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.timecard.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.PayListResponse.PayListResponseItem;

public class PayListUseCase_HourlyEmployee_ITTest extends PayListUseCase_AbstractPaymentTypeITTest {
	private static final double OVERTIME_WAGE_MULTIPLIER = 1.5d;
	
	private static final LocalDate LAST_FRIDAY = LocalDate.of(2015, 11, 27);	//in previous period
	private static final LocalDate LAST_SATURDAY = LocalDate.of(2015, 11, 28);	//this period start
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);	//this period end
	private static final LocalDate THIS_SATURDAY = LocalDate.of(2015, 12, 05);	//in next period

	private static final LocalDate A_PAYDAY = THIS_FRIDAY;
	private static final LocalDate NOT_A_PAYDAY = THIS_SATURDAY;
	
	private final int employeeId = 1;
	private final int hourlyWage = 10;
	
	private abstract class Case {
		List<AddTimeCardRequest> timeCards;
		int thenPayCheckGrossAmount;
	}
	
	private class NoTimeCard_ThenZeroAmountPayCheck extends Case {{
		timeCards = Collections.emptyList();
		thenPayCheckGrossAmount = 0;
	}}
	
	private class OneTimeCard extends Case {{
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 8));
		}};
		thenPayCheckGrossAmount = hourlyWage * (8);
	}}

	private class TwoTimeCards_AtBordersOfPayPeriod extends Case {{
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, LAST_SATURDAY, 4));
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 8));
		}};
		thenPayCheckGrossAmount = hourlyWage * (4 + 8);
	}}

	private class ThreeTimeCards_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods extends Case {{
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, LAST_FRIDAY, 3)); 	//In previous pay period, should be IGNORED
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 5)); 	//In this pay period
			add(new AddTimeCardRequest(employeeId, THIS_SATURDAY, 7)); 	//In next pay period, should be IGNORED
		}};
		thenPayCheckGrossAmount = hourlyWage * (5);
	}}
	
	private class OneTimeCard_OverTimeWorkingHours extends Case {{
		timeCards = new ArrayList<AddTimeCardRequest>() {{
			add(new AddTimeCardRequest(employeeId, THIS_FRIDAY, 12));
		}};
		int normalWage = hourlyWage * (8);
		int overTimeWage = (int) (hourlyWage * OVERTIME_WAGE_MULTIPLIER * (4));
		thenPayCheckGrossAmount = normalWage + overTimeWage;
	}}
	
	public PayListUseCase_HourlyEmployee_ITTest(DatabaseProvider databaseProvider) {
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
		givenWhenThen(new OneTimeCard_OverTimeWorkingHours());
	}
	
	private void givenWhenThen(Case theCase) {
		givenAHourlyEmployee();
		givenTimeCards(theCase.timeCards);
		
		Collection<PayListResponseItem> payChecks = whenGeneratePayUseCaseExecuted(getAPayday());
		
		thenPayCheckGrossAmountShouldBe(payChecks, theCase.thenPayCheckGrossAmount);
	}

	private void givenAHourlyEmployee() {
		useCaseFactories.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, "", "", hourlyWage));
	}

	private void givenTimeCards(List<AddTimeCardRequest> addTimeCardRequests) {
		for (AddTimeCardRequest addTimeCardRequest : addTimeCardRequests) {
			useCaseFactories.addTimeCardUseCase().execute(addTimeCardRequest);
		}
	}

	@Override
	protected void givenAnEmployee() {
		givenAHourlyEmployee();
	}

	@Override
	protected LocalDate getNotAPayday() {
		return NOT_A_PAYDAY;
	}

	@Override
	protected LocalDate getAPayday() {
		return A_PAYDAY;
	}

}
