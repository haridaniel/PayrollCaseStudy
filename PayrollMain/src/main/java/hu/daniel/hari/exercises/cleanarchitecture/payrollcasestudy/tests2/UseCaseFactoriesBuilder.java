package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests2;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAll;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class UseCaseFactoriesBuilder {
	private Database database;
	private BankTransferPort bankTransferPort;

	public UseCaseFactoriesBuilder withDatabase(Database database) {
		this.database = database;
		return this;
	}

	public UseCaseFactoriesBuilder withBankTransferPort(BankTransferPort bankTransferPort) {
		this.bankTransferPort = bankTransferPort;
		return this;
	}

	public UseCaseFactoriesAll buildUseCaseFactories() {
		checkBuildability();
		UseCaseFactoriesAllImpl useCaseFactories = new UseCaseFactoriesAllImpl(database, bankTransferPort);
		return useCaseFactories;
	}

	private void checkBuildability() {
		checkForNull(database, "database should be selected");
		checkForNull(bankTransferPort, "bankTransferPort should be selected");
	}

	private static void checkForNull(Object o, String message) {
		if (o == null) {
			throw new NullPointerException(message);
		}
	}

}