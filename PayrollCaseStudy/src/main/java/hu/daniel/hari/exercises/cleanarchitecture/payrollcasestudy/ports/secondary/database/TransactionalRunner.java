package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database;

public interface TransactionalRunner {
	void executeInTransaction(Runnable runnable);
}
