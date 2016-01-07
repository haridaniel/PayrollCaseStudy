package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database;

import javax.persistence.EntityManager;

public interface Database {
	
	TransactionalRunner transactionalRunner();
	EntityFactory entityFactory();
	EmployeeGateway employeeGateway();

	
	@Deprecated
	EntityManager getEntityManager();
	
}