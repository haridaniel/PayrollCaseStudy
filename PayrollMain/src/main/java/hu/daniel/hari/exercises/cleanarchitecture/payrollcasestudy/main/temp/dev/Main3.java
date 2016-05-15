package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.dev;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.add.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PaymentFulfillRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PaymentFulfillResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.FunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;
import oracle.net.aso.d;

public class Main3 {
	
	public static void main(String[] args) {
		
		JPADatabaseModule jpaDatabaseModule = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB);
		Database database = jpaDatabaseModule.getDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();

		UseCaseFactoryImpl useCaseFactoryImpl = new UseCaseFactoryImpl(database.transactionalRunner(), database.employeeGateway(), bankTransferPort);
		
		useCaseFactoryImpl.deleteEmployeeUseCase();
		
		useCaseFactoryImpl.paymentFulfillUseCase();
		
		
		
		jpaDatabaseModule.close();
	}
	
	
	private static class UseCaseFactoryImpl implements 
		DeleteEmployeeUseCaseFactory,
		PaymentFulfillUseCaseFactory
	{
		private TransactionalRunner transactionalRunner;
		private EmployeeGateway employeeGateway;
		private BankTransferPort bankTransferPort;

		public UseCaseFactoryImpl(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway,
				BankTransferPort bankTransferPort) {
			this.transactionalRunner = transactionalRunner;
			this.employeeGateway = employeeGateway;
			this.bankTransferPort = bankTransferPort;
		}

		@Override
		public FunctionUseCase<PaymentFulfillRequest, PaymentFulfillResponse> paymentFulfillUseCase() {
			return new PaymentFulfillUseCase(employeeGateway, transactionalRunner, bankTransferPort);
		}

		@Override
		public CommandUseCase<DeleteEmployeeRequest> deleteEmployeeUseCase() {
			return new DeleteEmployeeUseCase(transactionalRunner, employeeGateway);
		}
		
	}

	
}