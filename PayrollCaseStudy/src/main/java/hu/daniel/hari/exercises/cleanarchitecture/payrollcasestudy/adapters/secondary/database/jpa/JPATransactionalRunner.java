package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class JPATransactionalRunner implements TransactionalRunner {
	@Inject private Provider<EntityManager> entityManagerProvider;

	@Override
	public void executeInTransaction(Runnable runnable) {
		EntityTransaction transaction = beginTransaction();
		try {
			runnable.run();
			transaction.commit();
			entityManagerProvider.get().clear();
		} catch (Throwable e) {
			if(transaction.isActive())
				transaction.rollback();
			throw e;
		}
	}
	
	private EntityTransaction beginTransaction() {
		EntityTransaction transaction = entityManagerProvider.get().getTransaction();
		transaction.begin();
		return transaction;
	}

}
