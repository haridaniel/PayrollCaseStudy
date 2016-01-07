package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import java.util.Arrays;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor.port.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.SendPayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main {
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		
		
		//Secondary ports
		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB).getDatabase();
//		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getDatabase();
//		Database database = new InMemoryDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();
		
		//Application
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database, bankTransferPort);
		
		//Primary ports
		new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactory);

		testPay(useCaseFactory);
		
		new SwingUI(useCaseFactory);
	}

	private void testPay(UseCaseFactory useCaseFactory) {
		PayCheck payCheck = new PayCheck(1, 1001, 0);
		useCaseFactory.sendPayUseCase().execute(new SendPayRequest(Arrays.asList(payCheck)));
	}
	
}
