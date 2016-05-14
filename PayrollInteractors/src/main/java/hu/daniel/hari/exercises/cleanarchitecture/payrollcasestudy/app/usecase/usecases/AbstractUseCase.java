package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

public class AbstractUseCase {
	private boolean executed;
	
	protected void checkFirstExecution() {
		if(executed)
			throw new UseCaseAlreadyExecutedException();
		executed = true;
	}
	
	private static class UseCaseAlreadyExecutedException extends RuntimeException {
	}
}
