package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class TransactionalUseCase<R extends Request> implements UseCase<R> {
	private final TransactionalRunner transactionalRunner;

	public TransactionalUseCase(TransactionalRunner transactionalRunner) {
		this.transactionalRunner = transactionalRunner;
	}

	@Override
	public final void execute(R request) {
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction(request);
		});
	}

	protected abstract void executeInTransaction(R request);

}