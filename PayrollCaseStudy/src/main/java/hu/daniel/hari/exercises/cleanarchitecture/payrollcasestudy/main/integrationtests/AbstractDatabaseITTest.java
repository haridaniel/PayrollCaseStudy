package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.jpa.StaticJPADatabaseTestContext;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.PersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;

@RunWith(Parameterized.class)
public abstract class AbstractDatabaseITTest {
//	private static final String PERSISTENCE_UNIT_NAME = PersistenceUnitNames.HSQL_DB;
	private static final String PERSISTENCE_UNIT_NAME = PersistenceUnitNames.POSTGRES_LOCAL_DB;

	@Parameterized.Parameters(name="{0}")
	public static Collection<Database[]> getDatabasesToTestOn() {
		return Arrays.asList(new Database[][] {
			
			{new InMemoryDatabase()},
			{StaticJPADatabaseTestContext.get(PERSISTENCE_UNIT_NAME).getDatabase()},
			
		});
	}

}