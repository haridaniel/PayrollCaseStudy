package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.userapi.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.ChangeEmployeeNameRequest;

public class PayrollAppBoundaryImpl implements PayrollAppBoundary {

	private Database database;

	public PayrollAppBoundaryImpl(Database database) {
		this.database = database;
	}

	@Override
	public void addSalariedEmployeeTransaction(int employeeId, String name, String address, int monthlySalary) {
		new AddSalariedEmployeeUseCase(null, null, null, null, null, null, null)
				.execute(new AddSalariedEmployeeRequest(employeeId, name, address, monthlySalary));

	}

	@Override
	public void deleteEmployeeTransaction(int employeeId) {
		new DeleteEmployeeUseCase(database.transactionalRunner(), database.employeeGateway())
				.execute(new DeleteEmployeeRequest(employeeId));

	}

	@Override
	public void changeEmployeeNameTransaction(int employeeId, String newName) {
		new ChangeEmployeeNameUseCase(null, null)
				.execute(new ChangeEmployeeNameRequest(employeeId, newName));

	}

}
