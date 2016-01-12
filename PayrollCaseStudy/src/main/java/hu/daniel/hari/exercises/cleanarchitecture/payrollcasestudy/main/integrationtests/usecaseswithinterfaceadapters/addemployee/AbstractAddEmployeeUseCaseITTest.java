package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.addemployee;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public abstract class AbstractAddEmployeeUseCaseITTest extends AbstractUseCaseITTest {

	public AbstractAddEmployeeUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	final int employeeId = 1;
	final String name = "Bob";
	final String address = "Liverside road";
	
	@Test
	public void testAddEmployeeUseCase() {
		when();
		then(database.employeeGateway().findById(employeeId));
	}

	private void when() {
		executeUseCase();
	}

	protected abstract void executeUseCase();

	private void then(Employee employee) {
		assertEmployeeDefaultFields(employee);
		assertEmployeeFields(employee, name, address);
		assertEmployeeTypeSpecificFields(employee, getPaymentTypeClass(), getPaymentScheduleClass());
		doAssertEmployeeTypeSpecificFields(employee);
	}

	private void assertEmployeeDefaultFields(Employee employee) {
		assertThat(employee.getPaymentMethod(), instanceOf(HoldPaymentMethod.class));
		assertThat(employee.getAffiliation(), instanceOf(NoAffiliation.class));
	}

	private void assertEmployeeFields(Employee employee, String name, String address) {
		assertNotNull(employee);
		assertEquals(employee.getName(), name);
		assertEquals(employee.getAddress(), address);
	}
	
	private void assertEmployeeTypeSpecificFields(Employee employee, Class<? extends PaymentType> paymentType, Class<? extends PaymentSchedule> paymentSchedule) {
		assertThat(employee.getPaymentType(), instanceOf(paymentType));
		assertThat(employee.getPaymentSchedule(), instanceOf(paymentSchedule));
	}

	protected abstract Class<? extends PaymentType> getPaymentTypeClass();
	
	protected abstract Class<? extends PaymentSchedule> getPaymentScheduleClass();
	
	protected abstract void doAssertEmployeeTypeSpecificFields(Employee employee);
	
}
