package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.changeaffiliation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;

public class AddUnionMemberAffiliationUseCaseITTest extends AbstractUseCaseITTest {

	private int employeeId = 1;
	private int unionMemberId = 7000;
	private int weeklyDueAmount = 135;

	public AddUnionMemberAffiliationUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void testAddUnionMemberAffiliationUseCase() {
		givenAnEmployee();
		whenAddingUnionMemberAffiliation();
		thenShouldBeUnionAffiliated(database.employeeGateway().findById(employeeId));
	}

	private void givenAnEmployee() {
		useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
	}

	private void whenAddingUnionMemberAffiliation() {
		useCaseFactory.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(employeeId, unionMemberId, weeklyDueAmount));
	}

	private void thenShouldBeUnionAffiliated(Employee employee) {
		assertThat(employee.getAffiliation(), instanceOf(UnionMemberAffiliation.class));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getUnionMemberId(), is(unionMemberId));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getWeeklyDueAmount(), is(weeklyDueAmount));
	}
}
