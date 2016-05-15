package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.dev;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.moneytransfer.mock.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.change.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.change.paymentmethod.ChangeToDirectPaymentMethodUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.change.paymentmethod.ChangeToPaymasterPaymentMethodUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAll;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesAllImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PaymentFulfillRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PaymentFulfillResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.FunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.ChangeEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.ChangeToAbstractPaymentMethodUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class Main4 {

	public static void main(String[] args) {

		JPADatabaseModule jpaDatabaseModule = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB);
		Database database = jpaDatabaseModule.getDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();

		EventBus eventBus = new EventBus();
		
		UseCaseFactoriesAll useCaseFactoriesAll = new UseCaseFactoriesAllImpl(database, bankTransferPort);
		
		new PayListController(useCaseFactoriesAll, useCaseFactoriesAll, eventBus);

		jpaDatabaseModule.close();
	}

}