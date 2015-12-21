package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.StaticIntegrationTestsConfig;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

@RunWith(Parameterized.class)
public abstract class ParameterizedMultipleDatabaseITTest {

	@Parameterized.Parameters(name = "{0}")
	public static Collection<Object[]> getDatabaseProvidersToTestOn() {
		return toListOfOneSizedArray(StaticIntegrationTestsConfig.DATABASE_PROVIDERS);
	}
	
	private static <T> List<Object[]> toListOfOneSizedArray(List<T> objects) {
		List<Object[]> list = new ArrayList<>();
		for (T object : objects) {
			list.add(new Object[]{object});
		}
		return list;
	}

	protected Database database;
	
	public ParameterizedMultipleDatabaseITTest(DatabaseProvider databaseProvider) {
		this.database = databaseProvider.get();
	}


}