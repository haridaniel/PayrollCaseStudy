package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.Request;

public abstract class SingleExecutableUseCase<R extends Request> implements UseCase<R>{
	boolean executed = false;
	
	@Override
	public final void execute(R request) {
		if(executed)
			throw new TriedToExecuteUseCaseMultipleTimesException();
		doExecute(request);
		executed = true;
	}

	protected abstract void doExecute(R request);

	public static class TriedToExecuteUseCaseMultipleTimesException extends RuntimeException {}
}
