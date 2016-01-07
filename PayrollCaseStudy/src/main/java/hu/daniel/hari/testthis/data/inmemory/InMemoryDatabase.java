package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory;

import javax.persistence.EntityManager;

import org.mockito.Mockito;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;

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
		return new InMemoryEntityFactory();
	}

}
