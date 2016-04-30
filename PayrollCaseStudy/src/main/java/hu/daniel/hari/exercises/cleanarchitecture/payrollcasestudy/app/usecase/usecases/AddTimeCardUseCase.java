package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddTimeCardUseCase extends TransactionalEmployeeGatewayUseCase<AddTimeCardRequest> {

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
	protected void executeInTransaction(AddTimeCardRequest request) {
		Employee employee = employeeGateway.findById(request.employeeId);
		HourlyPaymentType hourlyPaymentType = castHourlyPaymentType(employee.getPaymentType());
		
		if(!request.forceOverwrite && isTimeCardAlreadyExists(hourlyPaymentType, request.date))
			throw new TimeCardAlreadyExistsException();
		hourlyPaymentType.addTimeCard(createTimeCard(request));
	}

	private boolean isTimeCardAlreadyExists(HourlyPaymentType hourlyPaymentType, LocalDate date) {
		return !hourlyPaymentType.getTimeCardsIn(DateInterval.ofSingleDate(date)).isEmpty();
	}

	private HourlyPaymentType castHourlyPaymentType(PaymentType paymentType) {
		if(paymentType instanceof HourlyPaymentType) {
			return (HourlyPaymentType) paymentType;
		} else {
			throw new NotHourlyEmployeeException();
		}
	}

	private TimeCard createTimeCard(AddTimeCardRequest request) {
		return timeCardFactory.timeCard(request.date, request.workingHoursQty);
	}
	
	public static class NotHourlyEmployeeException extends RuntimeException {
	}

	public static class TimeCardAlreadyExistsException extends RuntimeException {
	}

	public static interface AddTimeCardUseCaseFactory {
		AddTimeCardUseCase addTimeCardUseCase();
	}
	
}
