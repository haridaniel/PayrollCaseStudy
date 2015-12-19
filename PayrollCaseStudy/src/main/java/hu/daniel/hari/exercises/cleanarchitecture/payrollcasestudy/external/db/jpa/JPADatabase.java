package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.AllEntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

@Singleton
public class JPADatabase implements Database {

	@Inject private JPAEntityGateway jpaEntityGateway;
	@Inject private JPATransactionalRunner jpaTransactionalRunner;
	
	@Inject private EntityManager entityManager;
	@Inject private AllEntityFactory allEntityFactory;

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

	@Override
	public AllEntityFactory allEntityFactory() {
		return allEntityFactory;
	}
	
}
