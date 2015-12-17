package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

public class InMemoryTransactionalRunner implements TransactionalRunner {

	/** transactional behavior not implemented for inmemory**/ 
	@Override
	public void executeInTransaction(Runnable runnable) {
		runnable.run();
	}

}
