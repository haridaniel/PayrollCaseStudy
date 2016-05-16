package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecaseswithinterfaceadapters;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.UseCaseFactoriesForGUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.ParameterizedMultipleDatabaseITTest;

public abstract class AbstractUseCaseITTest extends ParameterizedMultipleDatabaseITTest {

	protected UseCaseFactoriesForGUI useCaseFactories = new UseCaseFactoriesAllImpl(database, null);

	public AbstractUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

}
