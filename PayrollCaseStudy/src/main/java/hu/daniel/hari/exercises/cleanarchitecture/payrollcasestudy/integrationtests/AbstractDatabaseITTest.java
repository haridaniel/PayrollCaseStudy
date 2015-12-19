package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.jpa.StaticJPADatabaseTestContext;

@RunWith(Parameterized.class)
public abstract class AbstractDatabaseITTest {
//	private static final String PERSISTENCE_UNIT_NAME = "hsql-db";
	private static final String PERSISTENCE_UNIT_NAME = "postgres-local-db";

	@Parameterized.Parameters(name="{0}")
	public static Collection<Database[]> getDatabasesToTestOn() {
		return Arrays.asList(new Database[][] {
			
			{new InMemoryDatabase()},
			{StaticJPADatabaseTestContext.get(PERSISTENCE_UNIT_NAME).getDatabase()},
			
		});
	}

}