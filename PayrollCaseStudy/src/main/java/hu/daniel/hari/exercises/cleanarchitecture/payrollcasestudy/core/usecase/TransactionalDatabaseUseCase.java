package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;

public abstract class TransactionalDatabaseUseCase extends DatabaseUseCase {
	public TransactionalDatabaseUseCase(PayrollDatabase payrollDatabase) {
		super(payrollDatabase);
	}
	
	@Override
	public final void execute() {
		EntityTransaction transaction = payrollDatabase.getTransaction();
		try {
			executeInTransaction();
			transaction.commit();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}
	
	protected abstract void executeInTransaction();

}