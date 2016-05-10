package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.exception.UseCaseException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddTimeCardUseCase extends AbstractTimeCardUseCase<AddTimeCardRequest> {

	private TimeCardFactory timeCardFactory;

	public AddTimeCardUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			TimeCardFactory timeCardFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.timeCardFactory = timeCardFactory;
	}

	@Override
	protected void executeTimeCardOperation(AddTimeCardRequest request, HourlyPaymentType hourlyPaymentType) {
		validateTimeCardNotExists(request, hourlyPaymentType);
		hourlyPaymentType.addTimeCard(createTimeCard(request));
	}

	private void validateTimeCardNotExists(AddTimeCardRequest request, HourlyPaymentType hourlyPaymentType) {
		hourlyPaymentType.getTimeCard(request.date)
			.ifPresent((e) -> {
				throw new TimeCardAlreadyExistsException();
			});
	}

	private TimeCard createTimeCard(AddTimeCardRequest request) {
		return timeCardFactory.timeCard(request.date, request.workingHoursQty);
	}
	
	public static class TimeCardAlreadyExistsException extends UseCaseException {
	}

	public static interface AddTimeCardUseCaseFactory {
		AddTimeCardUseCase addTimeCardUseCase();
	}
	
}
