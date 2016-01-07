package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday.deductions;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;

public class PayDayUseCase_UnionMemberAffiliated_WeeklyDues_ITTest extends PayDayUseCase_AbstractUnionMemberAffiliated_ITTest {
	//Dues deducted on every friday
	private static final LocalDate LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS = LocalDate.of(2015, 12, 31);
	private static final LocalDate A_SALARIED_EMPLOYEE_PAYDAY = LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS;
	private static final int FRIDAYS_COUNT_IN_PAY_INTERVAL = 4;

	int weeklyDueAmount = 110;

	public PayDayUseCase_UnionMemberAffiliated_WeeklyDues_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPaySingleSalariedEmployee_WithUnionMemberAffiliation_ShouldWeeklyDues_BeDeducted() {
		givenASalariedEmployee_WithUnionMembershipAffiliation(weeklyDueAmount);
		Collection<PayCheck> payChecks = whenGeneratePayUseCaseExecuted(A_SALARIED_EMPLOYEE_PAYDAY);
		thenPayCheckDeductionsAmount_ShouldBeCorrect(payChecks);
	}

	private void thenPayCheckDeductionsAmount_ShouldBeCorrect(Collection<PayCheck> payChecks) {
		thenPayCheckDeductionsAmount_ShouldBe(payChecks, weeklyDueAmount * FRIDAYS_COUNT_IN_PAY_INTERVAL);
	}


	
}
