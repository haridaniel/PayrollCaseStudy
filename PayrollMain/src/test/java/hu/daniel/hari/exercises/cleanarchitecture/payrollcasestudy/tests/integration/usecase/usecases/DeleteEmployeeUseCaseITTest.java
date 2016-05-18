package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway.NoSuchEmployeeException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.AbstractMultipleDatabaseUseCaseTest;

public class DeleteEmployeeUseCaseITTest extends AbstractMultipleDatabaseUseCaseTest {
	private int employeeId = 1;

	public DeleteEmployeeUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}
	
	@Test(expected = NoSuchEmployeeException.class)
	public void givenAnEmployee_whenDeletingIt_thenShouldNotBeFound() {
		givenAnEmployee();
		whenDeletingIt();
		thenShouldNotBeFound();
	}

	private void givenAnEmployee() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
	}

	private void whenDeletingIt() {
		useCaseFactories.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(employeeId));
	}

	private void thenShouldNotBeFound() {
		database.employeeGateway().findById(employeeId);
	}
}
