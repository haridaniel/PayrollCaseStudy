package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;

public class InMemoryTransactionalRunner implements TransactionalRunner {

	/** transactional behavior not implemented for inmemory**/ 
	@Override
	public void executeInTransaction(Runnable runnable) {
		runnable.run();
	}

}
