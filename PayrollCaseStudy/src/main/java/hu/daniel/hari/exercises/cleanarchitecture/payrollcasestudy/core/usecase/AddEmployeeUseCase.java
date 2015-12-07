package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

import javax.persistence.EntityTransaction;

public abstract class AddEmployeeUseCase extends TransactionalDatabaseUseCase {
	protected int employeeId;
	protected String name;
	protected String address;
	
	public AddEmployeeUseCase(PayrollDatabase payrollDatabase, int employeeId, String name, String address) {
		super(payrollDatabase);
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
	
	@Override
	public void executeInTransaction() {
		
		Employee employee = payrollDatabase.factory().employee();
		
		employee.setId(employeeId);
		employee.setName(name);
		employee.setAddress(address);
		
		employee.setPaymentClassification(getPaymentClassification());
		employee.setPaymentSchedule(getPaymentSchedule());
		
		employee.setPaymentMethod(payrollDatabase.factory().holdPaymentMethod()); //Default
				
		payrollDatabase.addEmployee(employee);
	}

	protected abstract PaymentClassification getPaymentClassification();
	protected abstract PaymentSchedule getPaymentSchedule();

}
