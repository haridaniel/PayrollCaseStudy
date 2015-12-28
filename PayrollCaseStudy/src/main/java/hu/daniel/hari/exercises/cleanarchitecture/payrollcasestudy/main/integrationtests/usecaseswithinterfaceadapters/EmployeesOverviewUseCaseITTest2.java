package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.EmployeesOverviewUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem.PaymentClassificationType;

public class EmployeesOverviewUseCaseITTest2 extends AbstractUseCaseITTest {
	private int employeeId = 1;
	private String name = "Bela";
	private String address = "address"; 
	
	private abstract class Case {
		abstract void givenAnEmployee();
		PaymentClassificationType paymentClassificationType;
	}
	
	public EmployeesOverviewUseCaseITTest2(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void givenASalariedEmployee_whenExecuteEmployeesOverview_thenResponseShouldBeOK() {
		givenThenWhen(new Case() {
			@Override
			void givenAnEmployee() {
				useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, name, address, 0));
			}
			{
				paymentClassificationType = PaymentClassificationType.SALARIED;
			}
		});
	}
	
	@Test
	public void givenAHourlyEmployee_whenExecuteEmployeesOverview_thenResponseShouldBeOK() {
		givenThenWhen(new Case() {
			@Override
			void givenAnEmployee() {
				useCaseFactory.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, name, address, 0));
			}
			{
				paymentClassificationType = PaymentClassificationType.HOURLY;
			}
		});
	}
	
	@Test
	public void givenACommissionedEmployee_whenExecuteEmployeesOverview_thenResponseShouldBeOK() {
		givenThenWhen(new Case() {
			@Override
			void givenAnEmployee() {
				useCaseFactory.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(employeeId, name, address, 0, 0));
			}
			{
				paymentClassificationType = PaymentClassificationType.COMMISSIONED;
			}
		});
	}


	private void givenThenWhen(Case theCase) {
		theCase.givenAnEmployee();
		thenResponseShouldBeCorrect(whenExecuteEmployeesOverview(), theCase);
	}

	private EmployeesOverviewUseCaseResponse whenExecuteEmployeesOverview() {
		EmployeesOverviewUseCase employeesOverviewUseCase = useCaseFactory.employeesOverviewUseCase();
		employeesOverviewUseCase.execute(new EmptyRequest());
		return employeesOverviewUseCase.getResponse();
		
	}

	private void thenResponseShouldBeCorrect(EmployeesOverviewUseCaseResponse response, Case theCase) {
		assertThat(response.employeeItems.size(), is(1));
		assertEmployeeItem(TestUtils.singleResult(response.employeeItems), theCase);
	}

	private void assertEmployeeItem(EmployeeItem employeeItem, Case theCase) {
		assertThat(employeeItem.id, is(employeeId));
		assertThat(employeeItem.name, is(name));
		assertThat(employeeItem.address, is(address));
		assertThat(employeeItem.paymentClassificationType, is(theCase.paymentClassificationType));
	}

}
