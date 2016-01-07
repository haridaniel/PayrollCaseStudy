package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.addemployee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddSalariedEmployeeUseCaseITTest extends AbstractAddEmployeeUseCaseITTest {
	
	private final int monthlySalary = 15000;
	
	private AddSalariedEmployeeRequest addSalariedEmployeeRequest = new AddSalariedEmployeeRequest
			(employeeId, name, address, monthlySalary);
	
	public AddSalariedEmployeeUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected void executeUseCase() {
		useCaseFactory.addSalariedEmployeeUseCase().execute(addSalariedEmployeeRequest);
	}

	@Override
	protected Class<? extends PaymentClassification> getPaymentClassificationClass() {
		return SalariedPaymentClassification.class; 
	}

	@Override
	protected Class<? extends PaymentSchedule> getPaymentScheduleClass() {
		return MonthlyPaymentSchedule.class;
	}

	@Override
	protected void doAssertEmployeeTypeSpecificFields(Employee employee) {
		assertThat(((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary(), is(monthlySalary));
	}

}
