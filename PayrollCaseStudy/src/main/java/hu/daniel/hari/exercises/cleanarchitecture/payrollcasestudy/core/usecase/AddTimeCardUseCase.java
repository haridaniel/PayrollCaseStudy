package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;

public class AddTimeCardUseCase extends TransactionalUseCase<AddTimeCardRequest> {

	public AddTimeCardUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(AddTimeCardRequest request) {
		Employee employee = entityGateway.getEmployee(request.employeeId);
		
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
		return entityGateway.factory().timeCard(request.date, request.workingHoursQty);
	}
	
	public static class TriedToAddTimeCardToNonHourlyEmployeeException extends RuntimeException {
	}
	
}
