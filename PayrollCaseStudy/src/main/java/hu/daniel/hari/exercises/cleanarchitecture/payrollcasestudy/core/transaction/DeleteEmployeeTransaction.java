package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.db.PayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {

	private int employeeId;

	public DeleteEmployeeTransaction(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public void execute() {
		PayrollDatabase.get().deleteEmployee(employeeId);
	}

}
