package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddEmployeeUseCase;

public class AddTimeCardUseCaseFactoryImpl implements AddTimeCardUseCaseFactory {

	private Database database;

	public AddTimeCardUseCaseFactoryImpl(Database database) {
		this.database = database;
	}

	@Override
	public AddTimeCardUseCase create() {
		return new AddTimeCardUseCase(database);
	}

}
