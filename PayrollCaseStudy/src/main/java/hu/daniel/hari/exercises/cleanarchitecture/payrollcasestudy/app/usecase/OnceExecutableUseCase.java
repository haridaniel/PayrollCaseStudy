package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;

public abstract class OnceExecutableUseCase<R extends Request> implements UseCase<R>{
	boolean executed = false;
	
	@Override
	public final void execute(R request) {
		if(executed)
			throw new TriedToExecuteUseCaseMultipleTimesException();
		executeOnce(request);
		executed = true;
	}

	protected abstract void executeOnce(R request);

	public static class TriedToExecuteUseCaseMultipleTimesException extends RuntimeException {}
}
