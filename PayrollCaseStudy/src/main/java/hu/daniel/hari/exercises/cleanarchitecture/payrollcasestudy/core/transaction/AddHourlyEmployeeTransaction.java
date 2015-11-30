package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {

	private int hourlyRate;

	public AddHourlyEmployeeTransaction(int employeeId, String name, String address, int hourlyRate) {
		super(employeeId, name, address);
		this.hourlyRate = hourlyRate;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return new HourlyPaymentClassification(hourlyRate);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return new WeeklyPaymentSchedule();
	}
	
}
