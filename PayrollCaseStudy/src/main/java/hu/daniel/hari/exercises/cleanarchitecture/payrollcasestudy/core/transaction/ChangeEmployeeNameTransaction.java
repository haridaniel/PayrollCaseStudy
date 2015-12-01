package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeNameTransaction extends PayrollDatabaseTransaction {

	private int employeeId;
	private String newName;

	public ChangeEmployeeNameTransaction(PayrollDatabase payrollDatabase, int employeeId, String newName) {
		super(payrollDatabase);
		this.employeeId = employeeId;
		this.newName = newName;
	}

	@Override
	public void execute() {
		Employee employee = payrollDatabase.getEmployee(employeeId);
		employee.setName(newName);
	}

	
}
