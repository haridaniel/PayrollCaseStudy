package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;

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
