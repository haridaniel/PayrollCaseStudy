package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database;

import javax.persistence.EntityManager;

public interface Database {
	
	TransactionalRunner transactionalRunner();
	EmployeeGateway employeeGateway();
	EntityFactory entityFactory();

	
	@Deprecated
	EntityManager getEntityManager();
	
}