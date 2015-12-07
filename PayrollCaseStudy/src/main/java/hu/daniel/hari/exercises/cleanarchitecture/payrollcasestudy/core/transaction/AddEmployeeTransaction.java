package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

import javax.persistence.EntityTransaction;

public abstract class AddEmployeeTransaction extends PayrollDatabaseTransaction {
	protected int employeeId;
	protected String name;
	protected String address;
	
	public AddEmployeeTransaction(PayrollDatabase payrollDatabase, int employeeId, String name, String address) {
		super(payrollDatabase);
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
	
	@Override
	public void execute() {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		
		Employee employee = payrollDatabase.create().employee();
		
		employee.setId(employeeId);
		employee.setName(name);
		employee.setAddress(address);
		
		employee.setPaymentClassification(getPaymentClassification());
		employee.setPaymentSchedule(getPaymentSchedule());
		
		employee.setPaymentMethod(payrollDatabase.create().holdPaymentMethod()); //Default
				
		payrollDatabase.addEmployee(employee);

		transaction.commit();
	}

	protected abstract PaymentClassification getPaymentClassification();
	protected abstract PaymentSchedule getPaymentSchedule();

}
