package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;

public abstract class PayrollDatabaseTransaction implements Transaction {
	protected PayrollDatabase payrollDatabase;

	public PayrollDatabaseTransaction(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

}