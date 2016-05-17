package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.pay.list.deductions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.testutil.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.pay.list.AbstractPayListUseCase_ITTest;

public abstract class PayListUseCase_AbstractUnionMemberAffiliated_ITTest extends AbstractPayListUseCase_ITTest {

	private final int employeeId = 1;
	protected int unionMemberId = 7000;

	public PayListUseCase_AbstractUnionMemberAffiliated_ITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	protected void givenASalariedEmployee_WithUnionMembershipAffiliation(int weeklyDueAmount) {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
		useCaseFactories.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(employeeId, unionMemberId, weeklyDueAmount));
	}

	protected void thenPayCheckDeductionsAmount_ShouldBe(Collection<PayListResponseItem> payChecks, int payCheckDeductionsAmount) {
		assertThat(TestUtils.singleResult(payChecks).deductionsAmount, is(payCheckDeductionsAmount));
	}

}