package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeNameUseCase extends TransactionalDatabaseUseCase {

	private int employeeId;
	private String newName;

	public ChangeEmployeeNameUseCase(PayrollDatabase payrollDatabase, int employeeId, String newName) {
		super(payrollDatabase);
		this.employeeId = employeeId;
		this.newName = newName;
	}

	@Override
	public void executeInTransaction() {
		Employee employee = payrollDatabase.getEmployee(employeeId);
		employee.setName(newName);
	}

	
}
