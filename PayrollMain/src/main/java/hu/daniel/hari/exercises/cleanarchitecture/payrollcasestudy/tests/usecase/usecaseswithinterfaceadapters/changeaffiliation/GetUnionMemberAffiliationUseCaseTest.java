package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecaseswithinterfaceadapters.changeaffiliation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.GetUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecaseswithinterfaceadapters.AbstractUseCaseITTest;

public class GetUnionMemberAffiliationUseCaseTest extends AbstractUseCaseITTest {

	private int employeeId = 1;
	private int unionMemberId = 7000;
	private int weeklyDueAmount = 135;

	public GetUnionMemberAffiliationUseCaseTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testGetUnionMemberAffiliationUseCase() {
		givenAnEmployeeWithUnionMemberAffiliation();
		thenResponseShouldBeCorrect(whenExecuteUseCase());
	}

	private void givenAnEmployeeWithUnionMemberAffiliation() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
		useCaseFactories.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(employeeId, unionMemberId, weeklyDueAmount));
	}

	private GetUnionMemberAffiliationResponse whenExecuteUseCase() {
		return useCaseFactories.getUnionMemberAffiliationUseCase()
				.execute(new GetUnionMemberAffiliationRequest(employeeId));
	}

	private void thenResponseShouldBeCorrect(GetUnionMemberAffiliationResponse response) {
		assertThat(response.employeeId, is(employeeId));
		assertThat(response.unionMemberId, is(unionMemberId));
		assertThat(response.weeklyDueAmount, is(weeklyDueAmount));
	}


	
}
