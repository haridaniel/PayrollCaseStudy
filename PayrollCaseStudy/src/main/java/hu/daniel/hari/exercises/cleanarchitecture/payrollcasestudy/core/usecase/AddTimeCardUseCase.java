package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.exception.NoSuchEmployeeException;

public class AddTimeCardUseCase extends TransactionalDatabaseUseCase {

	private AddTimeCardRequestModel requestModel;

	public AddTimeCardUseCase(PayrollDatabase payrollDatabase, AddTimeCardRequestModel requestModel) {
		super(payrollDatabase);
		this.requestModel = requestModel;
	}

	@Override
	protected void executeInTransaction() {
		Employee employee = payrollDatabase.getEmployee(requestModel.employeeId);
		assertNotNull(employee);
		
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

	private void assertNotNull(Employee employee) {
		if(employee == null)
			throw new NoSuchEmployeeException();
	}

	private TimeCard createTimeCard() {
		return payrollDatabase.factory().timeCard(requestModel.date, requestModel.workingHoursQty);
	}
	
	public static class TriedToAddTimeCardToNonHourlyEmployeeException extends RuntimeException {
	}
	
}
