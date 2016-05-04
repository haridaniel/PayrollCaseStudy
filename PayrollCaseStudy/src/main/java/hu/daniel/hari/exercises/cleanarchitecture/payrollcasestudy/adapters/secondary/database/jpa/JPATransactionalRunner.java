package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class JPATransactionalRunner implements TransactionalRunner {
	@Inject private Provider<EntityManager> entityManagerProvider;

	//http://stackoverflow.com/questions/10762974/should-jpa-entity-manager-be-closed
	//jvisualvm

	@Override
	public void executeInTransaction(Runnable runnable) {
		EntityManager entityManager = entityManagerProvider.get();
		System.err.println(entityManager);
		EntityTransaction transaction = createTransaction(entityManager);
//		EntityTransaction transaction = createTransaction();
		try {
			runnable.run();
			transaction.commit();
			entityManager.clear();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}
	
	private EntityTransaction createTransaction(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		return transaction;
	}

}
