package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

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
	public EntityFactory entityFactory() {
		return new InMemoryEntityFactory();
	}

}
