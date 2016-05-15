package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.dev;

import java.time.LocalDate;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ObservableValueImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.moneytransfer.mock.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAll;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.testdataloader.TestDataLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main5 {

	public static void main(String[] args) {
		JPADatabaseModule jpaDatabaseModule = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB);
		Database database = jpaDatabaseModule.getDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();

		EventBus eventBus = new EventBus();
		
		UseCaseFactoriesAll useCaseFactoriesAll = new UseCaseFactoriesAllImpl(database, bankTransferPort);
		
		new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactoriesAll);

		PayListController payListController = new PayListController(useCaseFactoriesAll, useCaseFactoriesAll, eventBus);

		PayListUIImpl payListUIImpl = new PayListUIImpl(payListController);
		
		ObservableValueImpl<LocalDate> observableDate = new ObservableValueImpl<LocalDate>(null);
		payListUIImpl.setObservableCurrentDate(observableDate);
		observableDate.set(LocalDate.of(2016, 05, 31));
		jpaDatabaseModule.close();
	}
	
	private static class PayListUIImpl extends PayListUI<PayListView> {
		public PayListUIImpl(PayListController controller) {
			super(controller, new ConsolePayListView());
			init();
		}
	}
	
	private static class ConsolePayListView implements PayListView {
		@Override
		public void setModel(PayListViewModel viewModel) {
			System.out.println(viewModel.payListViewItems);
		}
		
	}

}