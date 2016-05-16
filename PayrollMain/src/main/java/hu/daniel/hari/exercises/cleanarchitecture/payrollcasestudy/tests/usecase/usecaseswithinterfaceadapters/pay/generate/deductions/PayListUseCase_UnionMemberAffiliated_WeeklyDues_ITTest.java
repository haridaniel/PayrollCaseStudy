package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecaseswithinterfaceadapters.pay.generate.deductions;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.DatabaseProvider;

public class PayListUseCase_UnionMemberAffiliated_WeeklyDues_ITTest extends PayListUseCase_AbstractUnionMemberAffiliated_ITTest {
	//Dues deducted on every friday
	private static final LocalDate LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS = LocalDate.of(2015, 12, 31);
	private static final LocalDate A_SALARIED_EMPLOYEE_PAYDAY = LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS;
	private static final int FRIDAYS_COUNT_IN_PAY_INTERVAL = 4;

	int weeklyDueAmount = 110;

	public PayListUseCase_UnionMemberAffiliated_WeeklyDues_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPaySingleSalariedEmployee_WithUnionMemberAffiliation_ShouldWeeklyDues_BeDeducted() {
		givenASalariedEmployee_WithUnionMembershipAffiliation(weeklyDueAmount);
		Collection<PayListResponseItem> payChecks = whenGeneratePayUseCaseExecuted(A_SALARIED_EMPLOYEE_PAYDAY);
		thenPayCheckDeductionsAmount_ShouldBeCorrect(payChecks);
	}

	private void thenPayCheckDeductionsAmount_ShouldBeCorrect(Collection<PayListResponseItem> payChecks) {
		thenPayCheckDeductionsAmount_ShouldBe(payChecks, weeklyDueAmount * FRIDAYS_COUNT_IN_PAY_INTERVAL);
	}


	
}
