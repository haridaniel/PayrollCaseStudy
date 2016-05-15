package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.junit.Assert.*;

import org.junit.Test;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway.NoSuchEmployeeException;

public class DeleteEmployeeUseCaseITTest extends AbstractUseCaseITTest {
	private int employeeId = 1;

	public DeleteEmployeeUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
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
