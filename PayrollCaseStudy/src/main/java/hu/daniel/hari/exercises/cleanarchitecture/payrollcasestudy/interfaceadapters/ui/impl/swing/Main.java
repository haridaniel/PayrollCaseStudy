package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing;

import java.util.Arrays;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.direct.DirectPaymentFulFiller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.direct.DirectPaymentFulFillerMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.interactor.port.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.SendPayUseCaseRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class Main {
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		
		
		//Secondary ports
		Database database = new JPAPayrollDatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
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
		useCaseFactory.sendPayUseCase().execute(new SendPayUseCaseRequest(Arrays.asList(payCheck)));
	}
	
}
