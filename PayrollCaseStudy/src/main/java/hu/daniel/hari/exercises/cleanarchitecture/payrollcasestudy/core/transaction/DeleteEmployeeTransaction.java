package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;

public class DeleteEmployeeTransaction extends PayrollDatabaseTransaction {

	private int employeeId;

	public DeleteEmployeeTransaction(PayrollDatabase payrollDatabase, int employeeId) {
		super(payrollDatabase);
		this.employeeId = employeeId;
	}

	@Override
	public void execute() {
		payrollDatabase.deleteEmployee(employeeId);
	}

}
