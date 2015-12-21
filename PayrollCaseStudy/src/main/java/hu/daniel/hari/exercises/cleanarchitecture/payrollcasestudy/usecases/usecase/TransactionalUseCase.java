package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.Request;

public abstract class TransactionalUseCase<R extends Request> extends SingleExecutableUseCase<R> {
	private TransactionalRunner transactionalRunner;
	protected EmployeeGateway employeeGateway;

	public TransactionalUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		this.transactionalRunner = transactionalRunner;
		this.employeeGateway = employeeGateway;
	}

	@Override
	public final void doExecute(R request) {
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction(request);
		});
	}

	protected abstract void executeInTransaction(R request);

}