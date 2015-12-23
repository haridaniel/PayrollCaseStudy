package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddCommissionedEmployeeRequest;

public class PayDayUseCase_CommissionedPaymentClassification_ITTest extends AbstractPayDayUseCase_ITTest {
	private static final LocalDate AN_EVEN_FRIDAY = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY;
	private static final LocalDate AN_ODD_FRIDAY = AN_EVEN_FRIDAY.plusDays(7);

	private static final LocalDate A_PAYDAY = AN_EVEN_FRIDAY;
	private static final LocalDate NOT_A_PAYDAY = AN_ODD_FRIDAY;
	
	private static final LocalDate A_PAYPERIOD_START = AN_EVEN_FRIDAY.minusDays(13);
	private static final LocalDate A_PAYPERIOD_END = AN_EVEN_FRIDAY;
	
	private int employeeId = 1;
	private int biWeeklyBaseSalary = 500;
	private double commissionRate = 0.1d;

	private abstract class Case {
		List<AddSalesReceiptRequest> salesReceipts;
		int thenPayCheckNetAmountSum;
	}
	
	private class NoSalesReceipt_ThenBiWeeklyAmountPayCheck extends Case {{
		salesReceipts = Collections.emptyList();
		thenPayCheckNetAmountSum = biWeeklyBaseSalary;
	}}

	private class OneSalesReceipt extends Case {{
		salesReceipts = new ArrayList<AddSalesReceiptRequest>() {{
			add(new AddSalesReceiptRequest(employeeId, AN_EVEN_FRIDAY, 1000));
		}};
		int commisionAmount = (int) (commissionRate * (1000));
		thenPayCheckNetAmountSum = biWeeklyBaseSalary + commisionAmount;
	}}
	
	private class TwoSalesReceipt_AtBordersOfPayPeriod extends Case {{
		salesReceipts = new ArrayList<AddSalesReceiptRequest>() {{
			add(new AddSalesReceiptRequest(employeeId, A_PAYPERIOD_START, 1000));
			add(new AddSalesReceiptRequest(employeeId, A_PAYPERIOD_END, 1100));
		}};
		int commisionAmount = (int) (commissionRate * (1000 + 1100));
		thenPayCheckNetAmountSum = biWeeklyBaseSalary + commisionAmount;
	}}
	
	private class ThreeSalesReceipts_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods extends Case {{
		salesReceipts = new ArrayList<AddSalesReceiptRequest>() {{
			add(new AddSalesReceiptRequest(employeeId, A_PAYPERIOD_START.minusDays(1), 1100));  //should be ignored
			add(new AddSalesReceiptRequest(employeeId, A_PAYPERIOD_END, 1200));
			add(new AddSalesReceiptRequest(employeeId, A_PAYPERIOD_END.plusDays(1), 1300));		//should be ignored
		}};
		int commisionAmount = (int) (commissionRate * (1200));
		thenPayCheckNetAmountSum = biWeeklyBaseSalary + commisionAmount;
	}}

	public PayDayUseCase_CommissionedPaymentClassification_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_noSalesReceipts() throws Exception {
		givenWhenThen(new NoSalesReceipt_ThenBiWeeklyAmountPayCheck());
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_oneSalesReceipt() throws Exception {
		givenWhenThen(new OneSalesReceipt());
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_TwoSalesReceipt_AtBordersOfPayPeriod() throws Exception {
		givenWhenThen(new TwoSalesReceipt_AtBordersOfPayPeriod());
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_ThreeSalesReceipts_SpanningThreePayPeriods() throws Exception {
		givenWhenThen(new ThreeSalesReceipts_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods());
	}
	
	private void givenWhenThen(Case theCase) {
		givenACommissionedEmployee();
		givenSalesReceipts(theCase.salesReceipts);
		
		Collection<PayCheck> payChecks = whenPayDayUseCaseExecuted(getAPayday());
		
		thenPayChecksNetAmountSumShouldBe(payChecks, theCase.thenPayCheckNetAmountSum);
	}
	
	private void givenACommissionedEmployee() {
		useCaseFactory.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(employeeId, "", "", biWeeklyBaseSalary, commissionRate));
	}

	private void givenSalesReceipts(List<AddSalesReceiptRequest> salesReceipts) {
		for (AddSalesReceiptRequest addSalesReceiptRequest : salesReceipts) {
			useCaseFactory.addSalesReceiptUseCaseFactory().execute(addSalesReceiptRequest);
		}
	}

	private void thenPayChecksNetAmountSumShouldBe(Collection<PayCheck> payChecks, int netAmount) {
		int sumNetAmount = payChecks.stream()
				.mapToInt(payCheck -> payCheck.getNetAmount())
				.sum();
			assertThat(sumNetAmount, is(netAmount));
	}
	
	@Override
	protected void givenAnEmployee() {
		givenACommissionedEmployee();
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
