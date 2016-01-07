package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database;

public interface TransactionalRunner {
	void executeInTransaction(Runnable runnable);
}
