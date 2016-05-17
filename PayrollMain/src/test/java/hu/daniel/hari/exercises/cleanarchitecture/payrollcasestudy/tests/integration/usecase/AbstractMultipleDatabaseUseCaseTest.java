package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.UseCaseFactoriesForGUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.AbstractMultipleDatabaseTest;

public abstract class AbstractMultipleDatabaseUseCaseTest extends AbstractMultipleDatabaseTest {

	protected UseCaseFactoriesForGUI useCaseFactories = new UseCaseFactoriesAllImpl(database, null);

	public AbstractMultipleDatabaseUseCaseTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

}
