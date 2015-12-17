package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

import javax.persistence.EntityTransaction;

public abstract class AddEmployeeUseCase extends TransactionalUseCase {
	private int employeeId;
	private String name;
	private String address;
	
	private Employee employee;
	
	public AddEmployeeUseCase(Database database, int employeeId, String name, String address) {
		super(database);
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
	
	@Override
	protected final void executeInTransaction() {
		employee = entityGateway.factory().employee();
		
		setFields();
		setDefaultFields();
		setEmployeeTypeSpecificFields();
				
		entityGateway.addEmployee(employee);
	}

	private void setFields() {
		employee.setId(employeeId);
		employee.setName(name);
		employee.setAddress(address);
	}

	private void setDefaultFields() {
		employee.setPaymentMethod(entityGateway.factory().holdPaymentMethod());
		employee.setAffiliation(entityGateway.factory().noAffiliation());
	}

	private void setEmployeeTypeSpecificFields() {
		employee.setPaymentClassification(getPaymentClassification());
		employee.setPaymentSchedule(getPaymentSchedule());
	}

	protected abstract PaymentClassification getPaymentClassification();
	protected abstract PaymentSchedule getPaymentSchedule();

}
