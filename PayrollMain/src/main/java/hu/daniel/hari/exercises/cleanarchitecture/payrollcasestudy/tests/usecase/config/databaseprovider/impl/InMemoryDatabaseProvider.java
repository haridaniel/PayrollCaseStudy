package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.databaseprovider.impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.DatabaseProvider;

public class InMemoryDatabaseProvider implements DatabaseProvider {

	@Override
	public Database get() {
		return new InMemoryDatabase();
	}


}
