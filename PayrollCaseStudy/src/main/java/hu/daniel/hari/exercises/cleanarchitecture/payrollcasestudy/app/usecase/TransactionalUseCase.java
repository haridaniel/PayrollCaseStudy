package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class TransactionalUseCase<R extends Request> extends OnceExecutableUseCase<R> {
	private TransactionalRunner transactionalRunner;
	protected EmployeeGateway employeeGateway;

	public TransactionalUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		this.transactionalRunner = transactionalRunner;
		this.employeeGateway = employeeGateway;
	}

	@Override
	protected final void executeOnce(R request) {
		transactionalRunner.executeInTransaction(() -> {
			executeInTransaction(request);
		});
	}

	protected abstract void executeInTransaction(R request);

}