package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.addemployee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddCommissionedEmployeeUseCaseITTest extends AbstractAddEmployeeUseCaseITTest {
	
	private int biWeeklyBaseSalary = 9500;
	private double commissionRate = 0.1d;
	
	private AddCommissionedEmployeeRequest addCommissionedEmployeeRequest = 
			new AddCommissionedEmployeeRequest(employeeId, name, address, biWeeklyBaseSalary, commissionRate);
	
	public AddCommissionedEmployeeUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected void executeUseCase() {
		useCaseFactory.addCommissionedEmployeeUseCase().execute(addCommissionedEmployeeRequest);
	}

	@Override
	protected Class<? extends PaymentClassification> getPaymentClassificationClass() {
		return CommissionedPaymentClassification.class; 
	}

	@Override
	protected Class<? extends PaymentSchedule> getPaymentScheduleClass() {
		return BiWeeklyPaymentSchedule.class;
	}

	@Override
	protected void doAssertEmployeeTypeSpecificFields(Employee employee) {
		assertThat(((CommissionedPaymentClassification) employee.getPaymentClassification()).getBiWeeklyBaseSalary(), is(biWeeklyBaseSalary));
		assertThat(((CommissionedPaymentClassification) employee.getPaymentClassification()).getCommissionRate(), is(commissionRate));
	}

}
