package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;

public abstract class TransactionalDatabaseUseCase extends DatabaseUseCase {
	public TransactionalDatabaseUseCase(PayrollDatabase payrollDatabase) {
		super(payrollDatabase);
	}

	@Override
	public final void execute() {
		payrollDatabase.executeInTransaction(() -> {
			executeInTransaction();
		});
	}

	protected abstract void executeInTransaction();

}