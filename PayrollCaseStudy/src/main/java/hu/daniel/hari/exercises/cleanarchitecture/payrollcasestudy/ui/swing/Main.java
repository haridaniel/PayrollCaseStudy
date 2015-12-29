package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import java.awt.EventQueue;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class Main {
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		
//		Database database = new JPAPayrollDatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
		Database database = new InMemoryDatabase();
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);
		ViewFactory viewFactory = new ViewFactory(useCaseFactory);

		clearDatabaseAndInsertTestData(database, useCaseFactory);
		
		invokeLater(() -> {
			viewFactory.mainFrameView().doShow();
		});
	}

	private void clearDatabaseAndInsertTestData(Database database, UseCaseFactory useCaseFactory) {
		clearDatabase(database);
		insertTestEmployees(useCaseFactory);
	}

	private void clearDatabase(Database database) {
		database.transactionalRunner().executeInTransaction(() -> 
			database.employeeGateway().deleteAll()
		);
	}

	private void insertTestEmployees(AddEmployeeUseCaseFactory useCaseFactory) {
		useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(1, "Kovács Pista", "Vác, Damjanich u. 1.", 0));
		useCaseFactory.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(2, "Pandacsöki Boborján", "Budapest XI.", 0));
		useCaseFactory.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(5, "Takarékos Renáta", "Mende, Gyömrői út 2", 0, 0));
	}

	private void invokeLater(Runnable runnable) {
		EventQueue.invokeLater(runnable);
	}
	
}
