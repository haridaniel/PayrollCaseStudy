package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config;

import java.util.Arrays;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl.InMemoryDatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl.SingletonJPADatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.interfaceadapters.database.DatabaseProvider;

public class StaticIntegrationTestsConfig {

	public static final List<DatabaseProvider> DATABASE_PROVIDERS = Arrays.asList(
			new InMemoryDatabaseProvider(),
//			new SingletonJPADatabaseProvider(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB)
			new SingletonJPADatabaseProvider(JPAPersistenceUnitNames.HSQL_DB)
			);
	
}
