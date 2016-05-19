package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.UseCaseException;

public class AbstractUseCase {
	private boolean executed;
	
	protected void checkFirstExecution() {
		if(executed)
			throw new UseCaseAlreadyExecutedException();
		executed = true;
	}
	
	class UseCaseAlreadyExecutedException extends UseCaseException {
	}
}
