package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.find;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.Response;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.SalariedPaymentTypeResponse;

public abstract class AbstractFindEmployeesUseCaseITTest<T extends Response> extends AbstractUseCaseITTest {

	protected int employeeId = 1;

	private String name = "Bela";
	private String address = "address";

	abstract class Case {
		abstract void givenAnEmployee();
		Class<? extends PaymentTypeResponse> paymentTypeResponseClass;
	}
	
	public AbstractFindEmployeesUseCaseITTest(DatabaseProvider databaseProvider) {
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
				paymentTypeResponseClass = SalariedPaymentTypeResponse.class;
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
				paymentTypeResponseClass = HourlyPaymentTypeResponse.class;
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
				paymentTypeResponseClass = CommissionedPaymentTypeResponse.class;
			}
		});
	}

	private void givenThenWhen(Case theCase) {
		theCase.givenAnEmployee();
		thenResponseShouldBeCorrect(whenExecuteUseCase(), theCase);
	}

	private void thenResponseShouldBeCorrect(T response, Case theCase) {
		assertEmployeeItem(getSingleResultEmployeeItem(response), theCase);
	}

	private void assertEmployeeItem(EmployeeItem employeeItem, Case theCase) {
		assertThat(employeeItem.id, is(employeeId));
		assertThat(employeeItem.name, is(name));
		assertThat(employeeItem.address, is(address));
		assertThat(employeeItem.paymentTypeResponse, instanceOf(theCase.paymentTypeResponseClass));
	}

	protected abstract T whenExecuteUseCase();

	protected abstract EmployeeItem getSingleResultEmployeeItem(T response);

}