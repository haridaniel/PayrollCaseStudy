package hu.daniel.hari.testthis.data.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class InMemoryTransactionalRunner implements TransactionalRunner {

	/** transactional behavior not implemented for inmemory**/ 
	@Override
	public void executeInTransaction(Runnable runnable) {
		runnable.run();
	}

}
