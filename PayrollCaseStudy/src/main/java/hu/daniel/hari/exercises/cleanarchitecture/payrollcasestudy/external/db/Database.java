package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db;

import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

public interface Database {
	
	TransactionalRunner transactionalRunner();
	EmployeeGateway employeeGateway();
	EntityFactory entityFactory();

	
	@Deprecated
	EntityManager getEntityManager();
	
}