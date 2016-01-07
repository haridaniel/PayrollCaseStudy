package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.addemployee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;

public class AddHourlyEmployeeUseCaseITTest extends AbstractAddEmployeeUseCaseITTest {
	
	private int hourlyWage = 15;

	private AddHourlyEmployeeRequest addHourlyEmployeeRequest = new AddHourlyEmployeeRequest(employeeId, name, address, hourlyWage);
	
	public AddHourlyEmployeeUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected void executeUseCase() {
		useCaseFactory.addHourlyEmployeeUseCase().execute(addHourlyEmployeeRequest);
	}

	@Override
	protected Class<? extends PaymentClassification> getPaymentClassificationClass() {
		return HourlyPaymentClassification.class; 
	}

	@Override
	protected Class<? extends PaymentSchedule> getPaymentScheduleClass() {
		return WeeklyPaymentSchedule.class;
	}

	@Override
	protected void doAssertEmployeeTypeSpecificFields(Employee employee) {
		assertThat(((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage(), is(hourlyWage));
	}

}
