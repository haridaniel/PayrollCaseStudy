package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db;

import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

public interface Database {
	
	TransactionalRunner getTransactionalRunner();
	
	EntityGateway getEntityGateway();

	
	@Deprecated
	EntityManager getEntityManager();
	
	AllEntityFactory allEntityFactory();
	
}