package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class FakeTransactionalRunner implements TransactionalRunner {

	@Override
	public void executeInTransaction(final Runnable runnable) {
		System.out.println("transaction begins");
		runnable.run();
		System.out.println("transaction ends");
	}

}
