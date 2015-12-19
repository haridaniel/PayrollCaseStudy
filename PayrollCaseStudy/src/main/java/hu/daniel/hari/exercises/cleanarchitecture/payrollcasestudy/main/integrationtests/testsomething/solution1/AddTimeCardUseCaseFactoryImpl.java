package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;

public class AddTimeCardUseCaseFactoryImpl implements AddTimeCardUseCaseFactory {

	private Database database;

	public AddTimeCardUseCaseFactoryImpl(Database database) {
		this.database = database;
	}

	@Override
	public AddTimeCardUseCase create() {
		return new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), database.entityFactory());
	}

}
