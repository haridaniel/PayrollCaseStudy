package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

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

	private void insertTestEmployees(UseCaseFactory useCaseFactory) {
		useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(1, "Kovács Pista", "Vác, Damjanich u. 1.", 3000));
		useCaseFactory.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(2, "Pandacsöki Boborján", "Budapest XI.", 21));
		useCaseFactory.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(3, "Telki Zoltán", "Budapest Ovari u.", 25));
		useCaseFactory.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(5, "Takarékos Renáta", "Mende, Gyömrői út 2", 1650, 0.15d));

		//Paymentmethods
		useCaseFactory.changeToDirectPaymentMethodUseCase().execute(new ChangeToDirectPaymentMethodRequest(1, "16200223-10041865"));
		useCaseFactory.changeToDirectPaymentMethodUseCase().execute(new ChangeToDirectPaymentMethodRequest(2, "16200010-10001040"));
	}

}
