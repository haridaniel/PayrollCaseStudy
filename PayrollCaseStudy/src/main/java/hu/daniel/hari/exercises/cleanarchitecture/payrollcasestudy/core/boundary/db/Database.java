package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import javax.persistence.EntityManager;

public interface Database {
	
	TransactionalRunner getTransactionalRunner();
	
	EntityGateway getEntityGateway();

	@Deprecated
	EntityManager getEntityManager();
	
}