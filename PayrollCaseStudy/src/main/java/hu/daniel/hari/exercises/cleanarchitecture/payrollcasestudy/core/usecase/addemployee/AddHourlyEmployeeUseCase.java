package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class AddHourlyEmployeeUseCase extends AddEmployeeUseCase<AddHourlyEmployeeRequest> {

	public AddHourlyEmployeeUseCase(Database database) {
		super(database);
	}

	@Override
	protected PaymentClassification getPaymentClassification(AddHourlyEmployeeRequest request) {
		return entityGateway.factory().hourlyPaymentClassification(request.hourlyWage);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return entityGateway.factory().weeklyPaymentSchedule();
	}
	
}
