package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.Response;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class TransactionalFunctionUseCase<T extends Request, R extends Response> implements FunctionUseCase<T, R> {
	private final TransactionalRunner transactionalRunner;

	public TransactionalFunctionUseCase(TransactionalRunner transactionalRunner) {
		this.transactionalRunner = transactionalRunner;
	}
	
	@Override
	public final R execute(T request) {
		Holder<R> response = new Holder<>();
		transactionalRunner.executeInTransaction(() -> {
			response.value = executeInTransaction(request);
		});
		return response.value;
	}

	protected abstract R executeInTransaction(T request);
	
	private static class Holder<T> {
		public T value;
	}
	
}
