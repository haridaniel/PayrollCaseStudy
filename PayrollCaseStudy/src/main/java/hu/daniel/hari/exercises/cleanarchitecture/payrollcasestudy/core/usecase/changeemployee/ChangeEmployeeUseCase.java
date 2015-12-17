package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public abstract class ChangeEmployeeUseCase extends TransactionalUseCase {

	private int employeeId;

	public ChangeEmployeeUseCase(Database database, int employeeId) {
		super(database);
		this.employeeId = employeeId;
	}

	@Override
	protected final void executeInTransaction() {
		change(entityGateway.getEmployee(employeeId));
	}

	protected abstract void change(Employee employee);
	
}