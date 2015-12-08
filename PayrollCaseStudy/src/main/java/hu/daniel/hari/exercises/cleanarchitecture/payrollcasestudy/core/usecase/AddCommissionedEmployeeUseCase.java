package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class AddCommissionedEmployeeUseCase extends AddEmployeeUseCase {

	private int biWeeklyBaseSalary;
	private double commissionRate;

	public AddCommissionedEmployeeUseCase(PayrollDatabase payrollDatabase, 
			int employeeId, String name, String address, int biWeeklyBaseSalary, double commissionRate) {
		super(payrollDatabase, employeeId, name, address);
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return payrollDatabase.factory().commissionedPaymentClassification(biWeeklyBaseSalary, commissionRate);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return payrollDatabase.factory().biWeeklyPaymentSchedule();
	}

}
