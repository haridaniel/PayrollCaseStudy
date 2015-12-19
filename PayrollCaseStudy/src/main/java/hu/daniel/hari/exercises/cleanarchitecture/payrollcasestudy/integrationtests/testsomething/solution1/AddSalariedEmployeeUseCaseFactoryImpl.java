package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

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
