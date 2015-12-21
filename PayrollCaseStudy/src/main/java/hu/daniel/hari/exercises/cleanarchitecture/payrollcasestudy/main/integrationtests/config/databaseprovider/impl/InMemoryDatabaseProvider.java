package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

public class InMemoryDatabaseProvider implements DatabaseProvider {

	@Override
	public Database get() {
		return new InMemoryDatabase();
	}


}
