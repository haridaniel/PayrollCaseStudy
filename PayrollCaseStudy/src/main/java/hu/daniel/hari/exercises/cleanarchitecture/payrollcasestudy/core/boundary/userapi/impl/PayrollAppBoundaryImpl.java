package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.ChangeEmployeeNameUseCase;

public class PayrollAppBoundaryImpl implements PayrollAppBoundary {

	private Database database;

	public PayrollAppBoundaryImpl(Database database) {
		this.database = database;
	}

	@Override
	public void addSalariedEmployeeTransaction(int employeeId, String name, String address, int monthlySalary) {
		new AddSalariedEmployeeUseCase(database)
				.execute(new AddSalariedEmployeeRequest(employeeId, name, address, monthlySalary));

	}

	@Override
	public void deleteEmployeeTransaction(int employeeId) {
		new DeleteEmployeeUseCase(database)
				.execute(new DeleteEmployeeRequest(employeeId));

	}

	@Override
	public void changeEmployeeNameTransaction(int employeeId, String newName) {
		new ChangeEmployeeNameUseCase(database)
				.execute(new ChangeEmployeeNameRequest(employeeId, newName));

	}

}
