package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public abstract class TransactionalUseCase<T extends Request> implements UseCase<T> {
	private TransactionalRunner transactionalRunner;
	protected EmployeeGateway employeeGateway;

	public TransactionalUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		this.transactionalRunner = transactionalRunner;
		this.employeeGateway = employeeGateway;
	}

	@Override
	public final void execute(T request) {
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction(request);
		});
	}

	protected abstract void executeInTransaction(T request);

}