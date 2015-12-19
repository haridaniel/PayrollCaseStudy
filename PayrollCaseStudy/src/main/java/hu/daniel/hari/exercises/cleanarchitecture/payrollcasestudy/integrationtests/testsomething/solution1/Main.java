package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.testsomething.solution1;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.jpa.StaticJPADatabaseTestContext;

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
