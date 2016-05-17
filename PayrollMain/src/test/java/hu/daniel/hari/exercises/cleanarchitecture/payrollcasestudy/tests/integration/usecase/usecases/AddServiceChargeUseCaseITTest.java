package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.testutil.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.AbstractMultipleDatabaseUseCaseTest;

public class AddServiceChargeUseCaseITTest extends AbstractMultipleDatabaseUseCaseTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private int unionMemberId = 7000;
	private int serviceChargeAmount = 25000;
	private LocalDate serviceChargeDate = A_DATE;

	public AddServiceChargeUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}
	
	@Test
	public void testAddServiceChargeUseCase() {
		givenAUnionMemberAffiliatedEmployee();
		whenAddingServiceCharge();
		thenServiceChargeShouldBeAdded(database.employeeGateway().findById(employeeId));
	}

	private void givenAUnionMemberAffiliatedEmployee() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
		useCaseFactories.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(employeeId, unionMemberId, 0));
	}

	private void whenAddingServiceCharge() {
		useCaseFactories.addServiceChargeUseCase().execute(new AddServiceChargeRequest(unionMemberId, serviceChargeDate, serviceChargeAmount));
	}

	private void thenServiceChargeShouldBeAdded(Employee employee) {
		ServiceCharge serviceCharge = TestUtils.singleResult(((UnionMemberAffiliation) employee.getAffiliation())
				.getServiceChargesIn(DateInterval.ofSingleDate(serviceChargeDate)));
		assertThat(serviceCharge.getAmount(), is(serviceChargeAmount));
	}
}
