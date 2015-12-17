package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase {

	private String newName;

	public ChangeEmployeeNameUseCase(Database database, int employeeId, String newName) {
		super(database, employeeId);
		this.newName = newName;
	}

	@Override
	protected void change(Employee employee) {
		employee.setName(newName);
	}

	
}
