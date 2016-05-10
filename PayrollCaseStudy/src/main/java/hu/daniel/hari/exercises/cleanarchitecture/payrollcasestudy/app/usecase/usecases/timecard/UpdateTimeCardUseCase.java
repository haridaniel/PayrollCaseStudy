package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.UpdateTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.exception.UseCaseException;
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
	
	public static class TimeCardNotExistsException extends UseCaseException {
	}

	public static interface UpdateTimeCardUseCaseFactory {
		UpdateTimeCardUseCase updateTimeCardUseCase();
	}
	
}
