package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class SingletonJPADatabaseProvider implements DatabaseProvider {
	private Log log = LogFactory.getLog(getClass());
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
		log.info("clearDatabaseState");
		database.transactionalRunner().executeInTransaction(() -> {
			database.employeeGateway().deleteAll();
		});
	}


}
