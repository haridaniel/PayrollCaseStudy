package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old.testsomething.solution1;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old.jpa.StaticJPADatabaseTestContext;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

public class Main {

	
	public static void main(String[] args) {
		new Main();
	}
	
	
	public Main() {
		Database database = new InMemoryDatabase();
		
		AddSalariedEmployeeUseCaseFactory addSalariedEmployeeUseCaseFactory = new AddSalariedEmployeeUseCaseFactoryImpl(database);
		AddTimeCardUseCaseFactory addTimeCardUseCaseFactory = new AddTimeCardUseCaseFactoryImpl(database);

		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		new AddSalariedEmployeeUseCaseController(addSalariedEmployeeUseCaseFactory, useCaseFactory).doIt(1, "name", "address", 5000);
		new AddTimeCardUseCaseController(addTimeCardUseCaseFactory).addTimeCard(1, LocalDate.now(), 8);
		
		
		
		
	}
	
}
