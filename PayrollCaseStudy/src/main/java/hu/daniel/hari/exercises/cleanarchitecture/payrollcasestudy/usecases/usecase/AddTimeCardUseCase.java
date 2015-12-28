package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.AddTimeCardRequest;

public class AddTimeCardUseCase extends TransactionalEmployeeUseCase<AddTimeCardRequest> {

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
		
		castHourlyPaymentClassification(employee.getPaymentClassification())
			.addTimeCard(createTimeCard(request));
	}

	private HourlyPaymentClassification castHourlyPaymentClassification(PaymentClassification paymentClassification) {
		if(paymentClassification instanceof HourlyPaymentClassification) {
			return (HourlyPaymentClassification) paymentClassification;
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
