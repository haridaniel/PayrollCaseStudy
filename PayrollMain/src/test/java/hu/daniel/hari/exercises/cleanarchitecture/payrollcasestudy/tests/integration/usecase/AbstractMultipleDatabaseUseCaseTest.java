package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.AbstractMultipleDatabaseTest;

public abstract class AbstractMultipleDatabaseUseCaseTest extends AbstractMultipleDatabaseTest {

	protected UseCaseFactories useCaseFactories = new UseCaseFactoriesImpl(database, null);

	public AbstractMultipleDatabaseUseCaseTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

}
