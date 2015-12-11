package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class AddHourlyEmployeeUseCase extends AddEmployeeUseCase {

	private int hourlyWage;

	public AddHourlyEmployeeUseCase(PayrollDatabase payrollDatabase, int employeeId, String name, String address, int hourlyWage) {
		super(payrollDatabase, employeeId, name, address);
		this.hourlyWage = hourlyWage;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return payrollDatabase.factory().hourlyPaymentClassification(hourlyWage);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return payrollDatabase.factory().weeklyPaymentSchedule();
	}
	
}
