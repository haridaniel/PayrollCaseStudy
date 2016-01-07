package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.testthis.data.inmemory.InMemoryDatabase;

public class InMemoryDatabaseProvider implements DatabaseProvider {

	@Override
	public Database get() {
		return new InMemoryDatabase();
	}


}
