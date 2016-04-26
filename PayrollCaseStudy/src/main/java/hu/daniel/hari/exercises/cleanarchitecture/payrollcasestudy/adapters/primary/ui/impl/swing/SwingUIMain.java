package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import com.google.inject.Guice;
import com.google.inject.Injector;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.moneytransfer.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.dev.TestDataLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factory_impl.UseCaseFactoriesImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class SwingUIMain {
	
	public static void main(String[] args) {
		new SwingUIMain();
	}

	public SwingUIMain() {
		
		
		//Secondary ports
		
//		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB).getDatabase();
//		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getDatabase();
		Database database = new InMemoryDatabase();
		
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();
		
		//Application
		UseCaseFactories useCaseFactories = new UseCaseFactoriesImpl(database, bankTransferPort);
		
		//Primary ports
		new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactories);

		//TODO: Guice exception swallows
		try {
			start(useCaseFactories);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void start(UseCaseFactories useCaseFactories) {
		Injector injector = Guice.createInjector(new SwingUIModule(useCaseFactories));
		injector.getInstance(MainFrameUI.class).show();
	}
	
}
