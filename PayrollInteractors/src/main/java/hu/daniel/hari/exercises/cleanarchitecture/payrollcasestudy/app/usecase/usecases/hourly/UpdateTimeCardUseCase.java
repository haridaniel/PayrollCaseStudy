package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.hourly;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.hourly.TimeCardNotExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.hourly.UpdateTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class UpdateTimeCardUseCase extends AbstractTimeCardUseCase<UpdateTimeCardRequest> {

	public UpdateTimeCardUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway 
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeTimeCardOperation(UpdateTimeCardRequest request, HourlyPaymentType hourlyPaymentType) {
		updateTimeCard(hourlyPaymentType.getTimeCard(request.date)
				.orElseThrow(() -> new TimeCardNotExistsException())
				, request);
	}

	private void updateTimeCard(TimeCard timeCard, UpdateTimeCardRequest request) {
		timeCard.setWorkingHourQty(request.workingHoursQty);
	}
	
}
