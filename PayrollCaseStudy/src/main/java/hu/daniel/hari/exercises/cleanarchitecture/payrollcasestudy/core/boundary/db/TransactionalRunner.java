package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

public interface TransactionalRunner {
	void executeInTransaction(Runnable runnable);
}
