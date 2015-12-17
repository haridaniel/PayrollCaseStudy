package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;


public class AddSalariedEmployeeUseCase extends AddEmployeeUseCase {

	private int monthlySalary;

	public AddSalariedEmployeeUseCase(Database database, int employeeId, String name, String address, int monthlySalary) {
		super(database, employeeId, name, address);
		this.monthlySalary = monthlySalary;
	}

	@Override
	protected PaymentClassification getPaymentClassification() {
		return entityGateway.factory().salariedPaymentClassification(monthlySalary);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return entityGateway.factory().monthlyPaymentSchedule();
	}

}
