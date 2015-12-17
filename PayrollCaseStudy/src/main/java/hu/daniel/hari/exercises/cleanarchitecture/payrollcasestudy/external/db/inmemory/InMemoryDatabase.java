package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import javax.persistence.EntityManager;

import org.mockito.Mockito;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

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

}
