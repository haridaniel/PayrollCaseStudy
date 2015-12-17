package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

@Singleton
public class JPADatabase implements Database {

	@Inject private EntityManager entityManager;
	@Inject private JPAEntityGateway jpaEntityGateway;
	@Inject private JPATransactionalRunner jpaTransactionalRunner;

	@Inject
	public JPADatabase() {
	}

	@Override
	public TransactionalRunner getTransactionalRunner() {
		return jpaTransactionalRunner;
	}

	@Override
	public EntityGateway getEntityGateway() {
		return jpaEntityGateway;
	}


	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
