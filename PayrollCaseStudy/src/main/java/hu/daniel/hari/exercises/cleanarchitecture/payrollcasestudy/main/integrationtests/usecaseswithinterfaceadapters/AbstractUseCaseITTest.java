package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.junit.Assert.fail;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.ParameterizedMultipleDatabaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public abstract class AbstractUseCaseITTest extends ParameterizedMultipleDatabaseITTest {

	protected UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database, null);

	public AbstractUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

}
