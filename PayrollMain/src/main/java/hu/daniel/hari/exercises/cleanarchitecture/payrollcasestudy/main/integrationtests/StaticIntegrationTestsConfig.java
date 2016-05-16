package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl.InMemoryDatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.databaseprovider.impl.SingletonJPADatabaseProvider;

public class StaticIntegrationTestsConfig {

	public static final List<DatabaseProvider> DATABASE_PROVIDERS = new ArrayList<DatabaseProvider>() {{
		add(new InMemoryDatabaseProvider());
		add(new SingletonJPADatabaseProvider(JPAPersistenceUnit.HSQL_DB));
//		add(new SingletonJPADatabaseProvider(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB));
//		add(new SingletonJPADatabaseProvider(JPAPersistenceUnitNames.ORACLE_LOCAL_DB));
	}};
	
}
