package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

@RunWith(Parameterized.class)
public abstract class AbstractMultipleDatabaseTest {
	
	private static final List<TestDatabaseProvider> DATABASE_PROVIDERS = new ArrayList<TestDatabaseProvider>() {{
		add(new InMemoryTestDatabaseProvider());
		add(new JPATestDatabaseProvider(JPAPersistenceUnit.HSQL_DB));
//		add(new JPATestDatabaseProvider(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB));
//		add(new JPATestDatabaseProvider(JPAPersistenceUnitNames.ORACLE_LOCAL_DB));
	}};

	@Parameterized.Parameters(name = "{0}")
	public static Collection<Object[]> databaseProviders() {
		return DATABASE_PROVIDERS.stream()
				.map((o) -> new Object[] { o })
				.collect(Collectors.toList());
	}
	
	protected Database database;
	
	public AbstractMultipleDatabaseTest(TestDatabaseProvider testDatabaseProvider) {
		this.database = testDatabaseProvider.get();
	}

	public interface TestDatabaseProvider {
		Database get();
		String toString();
	}
	
	private static class InMemoryTestDatabaseProvider implements TestDatabaseProvider {

		@Override
		public Database get() {
			return new InMemoryDatabase();
		}

		@Override
		public String toString() {
			return "InMemory";
		}
	}

	/** 
	 * Faking new instance of the database for tests by clearing it's state. 
	 * Not for parallel tests!
	 * **/
	private static class JPATestDatabaseProvider implements TestDatabaseProvider {
		private Log log = LogFactory.getLog(getClass());
		
		private Database database;

		public JPATestDatabaseProvider(JPAPersistenceUnit persistenceUnit) {
			this.database = JPADatabaseModule.createAndStart(persistenceUnit).getDatabase();
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

		@Override
		public String toString() {
			return "JPA";
		}

	}
	
	
}