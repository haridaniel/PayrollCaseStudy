package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;


public class AddSalariedEmployeeUseCase extends AddEmployeeUseCase<AddSalariedEmployeeRequest> {

	public AddSalariedEmployeeUseCase(Database database) {
		super(database);
	}

	@Override
	protected PaymentClassification getPaymentClassification(AddSalariedEmployeeRequest request) {
		return entityGateway.factory().salariedPaymentClassification(request.monthlySalary);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return entityGateway.factory().monthlyPaymentSchedule();
	}

}
