package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;

public class JPATransactionalRunner implements TransactionalRunner {
	@Inject private EntityManager entityManager;

	@Override
	public void executeInTransaction(Runnable runnable) {
		EntityTransaction transaction = createTransaction();
		try {
			runnable.run();
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			} else {
				System.err.println("DEBUG: NOT SUPPOSED TO SEE THIS");
			}
			throw e;
		}
	}
	
	private EntityTransaction createTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		return transaction;
	}



}
