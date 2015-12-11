package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

import javax.persistence.EntityTransaction;

public abstract class AddEmployeeUseCase extends TransactionalDatabaseUseCase {
	private int employeeId;
	private String name;
	private String address;
	
	private Employee employee;
	
	public AddEmployeeUseCase(PayrollDatabase payrollDatabase, int employeeId, String name, String address) {
		super(payrollDatabase);
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
	
	@Override
	protected final void executeInTransaction() {
		employee = payrollDatabase.factory().employee();
		
		setFields();
		setDefaultFields();
		setDescendentClassFields();
				
		payrollDatabase.addEmployee(employee);
	}

	private void setFields() {
		employee.setId(employeeId);
		employee.setName(name);
		employee.setAddress(address);
	}

	private void setDefaultFields() {
		employee.setPaymentMethod(payrollDatabase.factory().holdPaymentMethod());
		employee.setAffiliation(new NoAffiliation());
	}

	private void setDescendentClassFields() {
		employee.setPaymentClassification(getPaymentClassification());
		employee.setPaymentSchedule(getPaymentSchedule());
	}

	protected abstract PaymentClassification getPaymentClassification();
	protected abstract PaymentSchedule getPaymentSchedule();

}
