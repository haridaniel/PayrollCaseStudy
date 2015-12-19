package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces;

import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.details.EntityFactory;

public interface Database {
	
	TransactionalRunner transactionalRunner();
	EmployeeGateway employeeGateway();
	EntityFactory entityFactory();

	
	@Deprecated
	EntityManager getEntityManager();
	
}