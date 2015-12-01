package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

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
		Employee employee = new Employee();
		
		employee.id = employeeId;
		employee.setName(name);
		employee.address = address;
		
		employee.setPaymentClassification(getPaymentClassification());
		employee.paymentSchedule = getPaymentSchedule();
		employee.paymentMethod = new HoldPaymentMethod(); //Default
				
		payrollDatabase.addEmployee(employee);
	}

	protected abstract PaymentClassification getPaymentClassification();
	protected abstract PaymentSchedule getPaymentSchedule();

}
