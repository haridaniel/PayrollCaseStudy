package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.FakeBankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.GuiSwingImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.testdataloader.TestDataLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Payroll {
	
	public static ConcreteBuilder builder() {
		return new ConcreteBuilder();
	}
	
	@SuppressWarnings("unchecked")
	public static abstract class Builder<T extends Builder<T>> {
		private Database database;
		private BankTransferPort bankTransferPort;
		private boolean loadTestData;

		public T withDatabase(Database database) {
			this.database = database;
			return (T) this;
		}

		public T withBankTransferPort(BankTransferPort bankTransferPort) {
			this.bankTransferPort = bankTransferPort;
			return (T) this;
		}

		public T withLoadedTestData() {
			loadTestData = true;
			return (T) this;
		}
		
		public UseCaseFactories buildUseCaseFactories() {
			checkBuildability();
			UseCaseFactories useCaseFactories = new PayrollModule(database, bankTransferPort).getUseCaseFactories();
			loadTestDataIfRequested(useCaseFactories);
			return useCaseFactories;
		}

		private void checkBuildability() {
			checkForNull(database, "database should be selected");
			checkForNull(bankTransferPort, "bankTransferPort should be selected");
		}

		private void loadTestDataIfRequested(UseCaseFactories useCaseFactories) {
			if(loadTestData)
				loadTestData(useCaseFactories);
		}

		private void loadTestData(UseCaseFactories useCaseFactories) {
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
	
	public static class ConcreteBuilder extends Builder<ConcreteBuilder> {

		public ConcreteBuilder withDatabaseInMemory() {
			withDatabase(new InMemoryDatabase());
			return this;
		}
		public ConcreteBuilder withDatabaseJPA(JPAPersistenceUnit jpaPersistenceUnit) {
			withDatabase(JPADatabaseModule.createAndStart(jpaPersistenceUnit).getDatabase());
			return this;
		}
		public ConcreteBuilder withBankTransferPortFake() {
			withBankTransferPort(new FakeBankTransferPort());
			return this;
		}
		
		
	}
	
}
