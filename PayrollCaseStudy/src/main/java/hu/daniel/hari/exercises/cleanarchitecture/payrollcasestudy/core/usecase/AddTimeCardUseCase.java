package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;

public class AddTimeCardUseCase extends TransactionalUseCase {

	private AddTimeCardRequestModel requestModel;

	public AddTimeCardUseCase(Database database, AddTimeCardRequestModel requestModel) {
		super(database);
		this.requestModel = requestModel;
	}

	@Override
	protected void executeInTransaction() {
		Employee employee = entityGateway.getEmployee(requestModel.employeeId);
		
		castHourlyPaymentClassification(employee.getPaymentClassification())
			.addTimeCard(createTimeCard());
	}

	private HourlyPaymentClassification castHourlyPaymentClassification(PaymentClassification paymentClassification) {
		if(paymentClassification instanceof HourlyPaymentClassification) {
			return (HourlyPaymentClassification) paymentClassification;
		} else {
			throw new TriedToAddTimeCardToNonHourlyEmployeeException();
		}
	}

	private TimeCard createTimeCard() {
		return entityGateway.factory().timeCard(requestModel.date, requestModel.workingHoursQty);
	}
	
	public static class TriedToAddTimeCardToNonHourlyEmployeeException extends RuntimeException {
	}
	
}
