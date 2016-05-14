package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.newversion.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class TransactionalCommandUseCase<T extends Request> extends AbstractUseCase implements CommandUseCase<T> {
	private final TransactionalRunner transactionalRunner;

	public TransactionalCommandUseCase(TransactionalRunner transactionalRunner) {
		this.transactionalRunner = transactionalRunner;
	}
	
	@Override
	public final void execute(T request) {
		checkFirstExecution();
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction(request);
		});
	}

	protected abstract void executeInTransaction(T request);
	
}
