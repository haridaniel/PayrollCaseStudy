package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.dev;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.add.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main1 {
	
	public static void main(String[] args) {
		
		Database database = new JPADatabaseModule(JPAPersistenceUnit.HSQL_DB).getDatabase();
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();
		
		new AddSalariedEmployeeUseCase(
				database.transactionalRunner(), 
				database.employeeGateway(), 
				database.entityFactory(), 
				database.entityFactory(), 
				database.entityFactory(), 
				database.entityFactory(), 
				database.entityFactory()
				);
		
		new DeleteEmployeeUseCase(database.transactionalRunner(), database.employeeGateway());
		
		new PaymentFulfillUseCase(database.employeeGateway(), database.transactionalRunner(), bankTransferPort);
		
		
		
		
	}
	
}