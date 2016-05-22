package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.FakeBankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.PayrollModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.AbstractMultipleDatabaseTest;

public abstract class AbstractMultipleDatabaseUseCaseTest extends AbstractMultipleDatabaseTest {

	protected UseCaseFactories useCaseFactories = new PayrollModule(database, new FakeBankTransferPort()).getUseCaseFactories();

	public AbstractMultipleDatabaseUseCaseTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

}
