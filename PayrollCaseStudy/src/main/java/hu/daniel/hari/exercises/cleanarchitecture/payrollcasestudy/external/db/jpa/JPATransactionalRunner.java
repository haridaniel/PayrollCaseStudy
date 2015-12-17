package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

public class JPATransactionalRunner implements TransactionalRunner {
	@Inject private EntityManager entityManager;
	@Inject private TransactionProvider transactionProvider;

	@Override
	public void executeInTransaction(Runnable runnable) {
		EntityTransaction transaction = transactionProvider.getTransaction();
		try {
			runnable.run();
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			} else {
				System.out.println();
			}
			throw e;
		}

	}

}
