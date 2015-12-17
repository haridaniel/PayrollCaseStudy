package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;

public class DeleteEmployeeUseCase extends TransactionalUseCase {

	private int employeeId;

	public DeleteEmployeeUseCase(Database database, int employeeId) {
		super(database);
		this.employeeId = employeeId;
	}

	@Override
	protected void executeInTransaction() {
		entityGateway.deleteEmployee(employeeId);
	}

}
