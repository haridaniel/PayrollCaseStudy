package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {

	private int hourlyWage;

	public AddHourlyEmployeeTransaction(PayrollDatabase payrollDatabase, int employeeId, String name, String address, int hourlyWage) {
		super(payrollDatabase, employeeId, name, address);
		this.hourlyWage = hourlyWage;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return payrollDatabase.create().hourlyPaymentClassification(hourlyWage);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return payrollDatabase.create().weeklyPaymentSchedule();
	}
	
}
