package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.AddSalariedEmployeeTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.ChangeEmployeeNameTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.DeleteEmployeeTransaction;

public class PayrollAppBoundaryImpl implements PayrollAppBoundary {

	private PayrollDatabase payrollDatabase;

	public PayrollAppBoundaryImpl(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

	@Override
	public void addSalariedEmployeeTransaction(int employeeId, String name, String address, int monthlySalary) {
		new AddSalariedEmployeeTransaction(payrollDatabase, employeeId, name, address, monthlySalary)
				.execute();

	}

	@Override
	public void deleteEmployeeTransaction(int employeeId) {
		new DeleteEmployeeTransaction(payrollDatabase, employeeId)
				.execute();

	}

	@Override
	public void changeEmployeeNameTransaction(int employeeId, String newName) {
		new ChangeEmployeeNameTransaction(payrollDatabase, employeeId, newName)
				.execute();

	}

}
