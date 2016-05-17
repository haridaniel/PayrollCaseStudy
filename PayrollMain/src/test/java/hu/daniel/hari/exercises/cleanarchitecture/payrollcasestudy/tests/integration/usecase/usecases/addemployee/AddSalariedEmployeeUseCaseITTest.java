package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.addemployee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;

public class AddSalariedEmployeeUseCaseITTest extends AbstractAddEmployeeUseCaseITTest {
	
	private final int monthlySalary = 15000;
	
	private AddSalariedEmployeeRequest addSalariedEmployeeRequest = new AddSalariedEmployeeRequest
			(employeeId, name, address, monthlySalary);
	
	public AddSalariedEmployeeUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	@Override
	protected void executeUseCase() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(addSalariedEmployeeRequest);
	}

	@Override
	protected Class<? extends PaymentType> getPaymentTypeClass() {
		return SalariedPaymentType.class; 
	}

	@Override
	protected Class<? extends PaymentSchedule> getPaymentScheduleClass() {
		return MonthlyPaymentSchedule.class;
	}

	@Override
	protected void doAssertEmployeeTypeSpecificFields(Employee employee) {
		assertThat(((SalariedPaymentType) employee.getPaymentType()).getMonthlySalary(), is(monthlySalary));
	}

}
