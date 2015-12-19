package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import javax.persistence.EntityManager;

import org.mockito.Mockito;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.AllEntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class InMemoryDatabase implements Database {

	private InMemoryTransactionalRunner transactionalRunner = new InMemoryTransactionalRunner();
	private EntityGateway entityGateway = new InMemoryEntityGateway();

	public InMemoryDatabase() {
		
	}
	
	@Override
	public TransactionalRunner getTransactionalRunner() {
		return transactionalRunner;
	}

	@Override
	public EntityGateway getEntityGateway() {
		return entityGateway;
	}

	@Override
	public EntityManager getEntityManager() {
		return Mockito.mock(EntityManager.class);
	}

	@Override
	public AllEntityFactory allEntityFactory() {
		return new InMemoryAllEntityFactory();
	}

}
