package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

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
		
		castHourlyPaymentType(employee.getPaymentType())
			.addTimeCard(createTimeCard(request));
	}

	private HourlyPaymentType castHourlyPaymentType(PaymentType paymentType) {
		if(paymentType instanceof HourlyPaymentType) {
			return (HourlyPaymentType) paymentType;
		} else {
			throw new TriedToAddTimeCardToNonHourlyEmployeeException();
		}
	}

	private TimeCard createTimeCard(AddTimeCardRequest request) {
		return timeCardFactory.timeCard(request.date, request.workingHoursQty);
	}
	
	public static class TriedToAddTimeCardToNonHourlyEmployeeException extends RuntimeException {
	}

	public static interface AddTimeCardUseCaseFactory {
		AddTimeCardUseCase addTimeCardUseCase();
	}
	
}
