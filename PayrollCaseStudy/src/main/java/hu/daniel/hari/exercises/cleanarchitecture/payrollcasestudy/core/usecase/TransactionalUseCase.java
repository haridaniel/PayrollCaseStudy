package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request;

public abstract class TransactionalUseCase<T extends Request> implements UseCase<T> {
	private TransactionalRunner transactionalRunner;
	protected EntityGateway entityGateway;

	public TransactionalUseCase(Database database) {
		transactionalRunner = database.getTransactionalRunner();
		entityGateway = database.getEntityGateway();
	}

	@Override
	public final void execute(T request) {
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction(request);
		});
	}

	protected abstract void executeInTransaction(T request);

}