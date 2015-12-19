package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

public class StaticJPADatabaseTestContext {
	
	private static StaticJPADatabaseTestContext INSTANCE;

	public synchronized static StaticJPADatabaseTestContext get(String persistenceUnitName) {
		if(INSTANCE == null)
			INSTANCE = new StaticJPADatabaseTestContext(persistenceUnitName);
		return INSTANCE;
	}
	
	private Database database;

	public StaticJPADatabaseTestContext(String persistenceUnitName) {
		database = new JPAPayrollDatabaseModule(persistenceUnitName).getPayrollDatabase();
	}
	
	public Database getDatabase() {
		return database;
	}
	
	
}
