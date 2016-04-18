package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.userapi.impl;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

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
