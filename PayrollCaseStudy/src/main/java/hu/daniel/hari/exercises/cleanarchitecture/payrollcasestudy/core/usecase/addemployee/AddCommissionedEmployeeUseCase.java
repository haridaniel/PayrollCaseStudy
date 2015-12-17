package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class AddCommissionedEmployeeUseCase extends AddEmployeeUseCase {

	private int biWeeklyBaseSalary;
	private double commissionRate;

	public AddCommissionedEmployeeUseCase(Database database, 
			int employeeId, String name, String address, int biWeeklyBaseSalary, double commissionRate) {
		super(database, employeeId, name, address);
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return entityGateway.factory().commissionedPaymentClassification(biWeeklyBaseSalary, commissionRate);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return entityGateway.factory().biWeeklyPaymentSchedule();
	}

}
