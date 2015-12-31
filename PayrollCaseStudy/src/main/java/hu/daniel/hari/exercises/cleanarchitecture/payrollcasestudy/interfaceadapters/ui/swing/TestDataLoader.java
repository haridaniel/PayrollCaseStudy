package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class TestDataLoader {

	public void clearDatabaseAndInsertTestData(Database database, UseCaseFactory useCaseFactory) {
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

	
}
