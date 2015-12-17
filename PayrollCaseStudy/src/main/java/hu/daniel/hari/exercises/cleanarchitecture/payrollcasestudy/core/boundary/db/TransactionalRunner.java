package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

public interface TransactionalRunner {
	//run
	void executeInTransaction(Runnable runnable);
}
