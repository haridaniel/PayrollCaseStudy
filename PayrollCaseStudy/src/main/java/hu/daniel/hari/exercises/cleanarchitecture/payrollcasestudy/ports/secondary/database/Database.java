package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database;

import javax.persistence.EntityManager;

public interface Database {
	
	TransactionalRunner transactionalRunner();
	EntityFactory entityFactory();
	EmployeeGateway employeeGateway();

	
	@Deprecated
	EntityManager getEntityManager();
	
}