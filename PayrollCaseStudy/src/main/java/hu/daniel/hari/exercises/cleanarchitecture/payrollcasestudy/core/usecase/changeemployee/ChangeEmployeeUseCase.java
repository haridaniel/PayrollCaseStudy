package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalDatabaseUseCase;

public abstract class ChangeEmployeeUseCase extends TransactionalDatabaseUseCase {

	private int employeeId;

	public ChangeEmployeeUseCase(PayrollDatabase payrollDatabase, int employeeId) {
		super(payrollDatabase);
		this.employeeId = employeeId;
	}

	@Override
	protected final void executeInTransaction() {
		change(payrollDatabase.getEmployee(employeeId));
	}

	protected abstract void change(Employee employee);
	
}