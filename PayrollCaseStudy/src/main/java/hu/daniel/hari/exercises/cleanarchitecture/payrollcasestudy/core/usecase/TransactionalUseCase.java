package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;

public abstract class TransactionalUseCase implements UseCase {
	private TransactionalRunner transactionalRunner;
	protected EntityGateway entityGateway;

	public TransactionalUseCase(Database database) {
		transactionalRunner = database.getTransactionalRunner();
		entityGateway = database.getEntityGateway();
	}

	@Override
	public final void execute() {
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction();
		});
	}

	protected abstract void executeInTransaction();

}