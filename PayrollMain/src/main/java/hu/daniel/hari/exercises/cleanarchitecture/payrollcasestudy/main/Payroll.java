package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.GuiSwingImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAll;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.testdataloader.TestDataLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Payroll {
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Database database;
		private BankTransferPort bankTransferPort;
		private boolean loadTestData;

		public Builder withDatabase(Database database) {
			this.database = database;
			return this;
		}

		public Builder withBankTransferPort(BankTransferPort bankTransferPort) {
			this.bankTransferPort = bankTransferPort;
			return this;
		}

		public Builder withLoadedTestData() {
			loadTestData = true;
			return this;
		}
		
		public Builder withDatabaseInMemory() {
			withDatabase(new InMemoryDatabase());
			return this;
		}
		public Builder withDatabaseJPA(JPAPersistenceUnit jpaPersistenceUnit) {
			withDatabase(new JPADatabaseModule(jpaPersistenceUnit).getDatabase());
			return this;
		}
		public Builder withBankTransferPortMock() {
			withBankTransferPort(new BankTransferPortMock());
			return this;
		}
		
		public UseCaseFactoriesAll buildUseCaseFactories() {
			checkBuildiness();
			UseCaseFactoriesAllImpl useCaseFactories = new UseCaseFactoriesAllImpl(database, bankTransferPort);
			onUseCaseFactoriesBuilt(useCaseFactories);
			return useCaseFactories;
		}

		private void checkBuildiness() {
			checkForNull(database, "database should be selected");
			checkForNull(bankTransferPort, "bankTransferPort should be selected");
		}

		private void onUseCaseFactoriesBuilt(UseCaseFactoriesAllImpl useCaseFactories) {
			if(loadTestData)
				loadTestData(useCaseFactories);
		}

		private void loadTestData(UseCaseFactoriesAllImpl useCaseFactories) {
			new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactories);
		}

		private static void checkForNull(Object o, String message) {
			if (o == null) {
				throw new NullPointerException(message);
			}
		}

		public GuiSwingImpl buildGuiAdminSwing() {
			return new GuiSwingImpl(buildUseCaseFactories());
		}
		
	}
	
	public static class ConcreteBuilder extends Builder {
	}
	
}
