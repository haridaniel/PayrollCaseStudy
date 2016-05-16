package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.dev;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAll;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main4 {

	public static void main(String[] args) {

		JPADatabaseModule jpaDatabaseModule = new JPADatabaseModule(JPAPersistenceUnit.HSQL_DB);
		Database database = jpaDatabaseModule.getDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();

		EventBus eventBus = new EventBus();
		
		UseCaseFactoriesAll useCaseFactoriesAll = new UseCaseFactoriesAllImpl(database, bankTransferPort);
		
		new PayListController(useCaseFactoriesAll, useCaseFactoriesAll, eventBus);

		jpaDatabaseModule.close();
	}

}