package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;


public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction {

	private int monthlySalary;

	public AddSalariedEmployeeTransaction(int employeeId, String name, String address, int monthlySalary) {
		super(employeeId, name, address);
		this.monthlySalary = monthlySalary;
		
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return new SalariedPaymentClassification(monthlySalary);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return new MontlhyPaymentSchedule();
	}

}
