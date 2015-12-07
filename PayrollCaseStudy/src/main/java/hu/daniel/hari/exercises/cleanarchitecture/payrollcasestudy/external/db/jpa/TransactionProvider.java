package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionProvider {

	@Inject EntityManager entityManager;
	
	public EntityTransaction getTransaction() {
		return createTransaction();
	}

	private EntityTransaction createTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		return transaction;
	}

	private EntityTransaction createOrJoinToTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		if(!transaction.isActive()) 
			transaction.begin();
		return transaction;
	}
	
}
