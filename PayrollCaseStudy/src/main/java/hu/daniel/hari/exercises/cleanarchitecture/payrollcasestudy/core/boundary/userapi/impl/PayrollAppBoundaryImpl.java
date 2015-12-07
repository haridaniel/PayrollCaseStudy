package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase;

public class PayrollAppBoundaryImpl implements PayrollAppBoundary {

	private PayrollDatabase payrollDatabase;

	public PayrollAppBoundaryImpl(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

	@Override
	public void addSalariedEmployeeTransaction(int employeeId, String name, String address, int monthlySalary) {
		new AddSalariedEmployeeUseCase(payrollDatabase, employeeId, name, address, monthlySalary)
				.execute();

	}

	@Override
	public void deleteEmployeeTransaction(int employeeId) {
		new DeleteEmployeeUseCase(payrollDatabase, employeeId)
				.execute();

	}

	@Override
	public void changeEmployeeNameTransaction(int employeeId, String newName) {
		new ChangeEmployeeNameUseCase(payrollDatabase, employeeId, newName)
				.execute();

	}

}
