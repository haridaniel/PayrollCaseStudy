package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;

public class AddTimeCardTransaction extends PayrollDatabaseTransaction {

	private AddTimeCardRequestModel requestModel;

	public AddTimeCardTransaction(PayrollDatabase payrollDatabase, AddTimeCardRequestModel requestModel) {
		super(payrollDatabase);
		this.requestModel = requestModel;
	}

	@Override
	public void execute() {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		Employee employee = payrollDatabase.getEmployee(requestModel.employeeId);
		assertNotNull(employee);
		
		toHourlyPaymentClassification(employee.getPaymentClassification()).addTimeCard(toTimeCard());
		transaction.commit();
		
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
		return payrollDatabase.create().timeCard(requestModel.date, requestModel.workingHoursQty);
	}
	
}
