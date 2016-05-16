package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.dev;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.pay.PayListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.pay.PayListUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAll;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.testdataloader.TestDataLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main6 {

	public static void main(String[] args) {
		JPADatabaseModule jpaDatabaseModule = new JPADatabaseModule(JPAPersistenceUnit.HSQL_DB);
		Database database = jpaDatabaseModule.getDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();

		
		UseCaseFactoriesAll useCaseFactoriesAll = new UseCaseFactoriesAllImpl(database, bankTransferPort);
		
		new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactoriesAll);
		
		EventBus eventBus = new EventBus();

		PayListController controller = new PayListController(useCaseFactoriesAll, useCaseFactoriesAll, eventBus);
		PayListUIImpl payListUIImpl = new PayListUIImpl(controller , new PayListPanel());
		
		
		jpaDatabaseModule.close();
	}

}