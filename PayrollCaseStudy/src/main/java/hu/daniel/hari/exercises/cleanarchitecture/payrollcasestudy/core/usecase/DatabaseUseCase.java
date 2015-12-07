package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;

public abstract class DatabaseUseCase implements UseCase {

	protected PayrollDatabase payrollDatabase;

	public DatabaseUseCase(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

}