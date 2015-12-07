package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;

public class DeleteEmployeeUseCase extends TransactionalDatabaseUseCase {

	private int employeeId;

	public DeleteEmployeeUseCase(PayrollDatabase payrollDatabase, int employeeId) {
		super(payrollDatabase);
		this.employeeId = employeeId;
	}

	@Override
	public void executeInTransaction() {
		payrollDatabase.deleteEmployee(employeeId);
	}

}
