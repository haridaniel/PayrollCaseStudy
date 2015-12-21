package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;

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
