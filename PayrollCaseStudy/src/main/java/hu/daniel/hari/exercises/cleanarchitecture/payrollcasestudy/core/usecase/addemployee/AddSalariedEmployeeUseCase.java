package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;


public class AddSalariedEmployeeUseCase extends AddEmployeeUseCase {

	private int monthlySalary;

	public AddSalariedEmployeeUseCase(PayrollDatabase payrollDatabase, int employeeId, String name, String address, int monthlySalary) {
		super(payrollDatabase, employeeId, name, address);
		this.monthlySalary = monthlySalary;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return payrollDatabase.factory().salariedPaymentClassification(monthlySalary);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return payrollDatabase.factory().monthlyPaymentSchedule();
	}

}
