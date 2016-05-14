package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.moneytransfer.mock.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main1 {
	
	public static void main(String[] args) {
		
		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB).getDatabase();
		
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();
		
		
		
		
		
		
		
		
		
	}
	
}