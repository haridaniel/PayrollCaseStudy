package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.change;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase {

	private String newName;

	public ChangeEmployeeNameUseCase(PayrollDatabase payrollDatabase, int employeeId, String newName) {
		super(payrollDatabase, employeeId);
		this.newName = newName;
	}

	@Override
	protected void change(Employee employee) {
		employee.setName(newName);
	}

	
}
