package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.addemployee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;

public class AddCommissionedEmployeeUseCaseITTest extends AbstractAddEmployeeUseCaseITTest {
	
	private int biWeeklyBaseSalary = 9500;
	private double commissionRate = 0.1d;
	
	private AddCommissionedEmployeeRequest addCommissionedEmployeeRequest = 
			new AddCommissionedEmployeeRequest(employeeId, name, address, biWeeklyBaseSalary, commissionRate);
	
	public AddCommissionedEmployeeUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testAddCommissionedEmployeeUseCase() {
		testAddEmployeeUseCase();
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
