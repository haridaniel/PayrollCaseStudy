package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

@RunWith(Parameterized.class)
public abstract class ParameterizedMultipleDatabaseITTest {

	@Parameterized.Parameters(name = "{0}")
	public static Collection<Object[]> getDatabaseProvidersToTestOn() {
		return toListOfOneSizedArray(StaticIntegrationTestsConfig.DATABASE_PROVIDERS);
	}
	
	protected Database database;
	
	public ParameterizedMultipleDatabaseITTest(DatabaseProvider databaseProvider) {
		this.database = databaseProvider.get();
	}

	private static <T> List<Object[]> toListOfOneSizedArray(List<T> objects) {
		List<Object[]> list = new ArrayList<>();
		for (T object : objects) {
			list.add(new Object[]{object});
		}
		return list;
	}


}