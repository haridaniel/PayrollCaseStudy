package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import javax.persistence.EntityManager;

import org.mockito.Mockito;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class InMemoryDatabase implements Database {

	private InMemoryTransactionalRunner transactionalRunner = new InMemoryTransactionalRunner();
	private EmployeeGateway employeeGateway = new InMemoryEntityGateway();

	public InMemoryDatabase() {
		
	}
	
	@Override
	public TransactionalRunner transactionalRunner() {
		return transactionalRunner;
	}

	@Override
	public EmployeeGateway employeeGateway() {
		return employeeGateway;
	}

	@Override
	public EntityManager getEntityManager() {
		return Mockito.mock(EntityManager.class);
	}

	@Override
	public EntityFactory entityFactory() {
		return new InMemoryAllEntityFactory();
	}

}
