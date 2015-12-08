package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;

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
		
		HourlyPaymentClassification hourlyPaymentClassification = toHourlyPaymentClassification(employee.getPaymentClassification());
		
		hourlyPaymentClassification.addTimeCard(toTimeCard());
	}

	private HourlyPaymentClassification toHourlyPaymentClassification(PaymentClassification paymentClassification) {
		if(paymentClassification instanceof HourlyPaymentClassification) {
			return (HourlyPaymentClassification) paymentClassification;
		} else {
			throw new RuntimeException("Tried to add timecard to non-hourly employee");
		}
	}

	private void assertNotNull(Employee employee) {
		if(employee == null)
			throw new RuntimeException("No such employee");
	}

	private TimeCard toTimeCard() {
		return payrollDatabase.factory().timeCard(requestModel.date, requestModel.workingHoursQty);
	}
	
}
