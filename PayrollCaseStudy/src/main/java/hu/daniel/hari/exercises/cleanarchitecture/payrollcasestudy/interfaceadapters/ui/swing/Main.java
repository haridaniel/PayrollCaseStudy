package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class Main {
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		
//		Database database = new JPAPayrollDatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
		Database database = new InMemoryDatabase();
		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);

		new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactory);
	
		new SwingUI(useCaseFactory);
	}
	
	
}
