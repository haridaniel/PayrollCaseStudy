package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class AddCommissionedEmployeeUseCase extends AddEmployeeUseCase<AddCommissionedEmployeeRequest> {

	public AddCommissionedEmployeeUseCase(Database database) {
		super(database);
	}

	@Override
	protected PaymentClassification getPaymentClassification(AddCommissionedEmployeeRequest request) {
		return entityGateway.factory().commissionedPaymentClassification(request.biWeeklyBaseSalary, request.commissionRate);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return entityGateway.factory().biWeeklyPaymentSchedule();
	}

}
