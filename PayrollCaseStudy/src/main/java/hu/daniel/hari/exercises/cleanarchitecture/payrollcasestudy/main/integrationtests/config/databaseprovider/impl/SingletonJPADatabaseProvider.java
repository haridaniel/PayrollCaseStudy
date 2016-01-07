package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class SingletonJPADatabaseProvider implements DatabaseProvider {

	private Database database;

	public SingletonJPADatabaseProvider(String persistenceUnitName) {
		this.database = new JPADatabaseModule(persistenceUnitName).getDatabase();
	}
	
	@Override
	public Database get() {
		clearDatabaseState();
		return database;
	}

	private void clearDatabaseState() {
		System.err.println("clearDatabaseState");
		database.transactionalRunner().executeInTransaction(() -> {
			database.employeeGateway().deleteAll();
		});
	}


}
