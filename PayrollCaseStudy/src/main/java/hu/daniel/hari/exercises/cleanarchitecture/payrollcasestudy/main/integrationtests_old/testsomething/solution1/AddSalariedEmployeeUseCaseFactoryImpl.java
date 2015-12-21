package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;

public class AddSalariedEmployeeUseCaseFactoryImpl  implements AddSalariedEmployeeUseCaseFactory {

	private Database database;
	
	public AddSalariedEmployeeUseCaseFactoryImpl(Database database) {
		this.database = database;
	}

	@Override
	public AddSalariedEmployeeUseCase create() {
		return new AddSalariedEmployeeUseCase(null, null, null, null, null, null, null);
	}

}
