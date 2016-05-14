package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.newversion.usecases.TransactionalCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class DeleteEmployeeUseCase extends TransactionalCommandUseCase<DeleteEmployeeRequest> {
	private EmployeeGateway employeeGateway;

	public DeleteEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner);
		this.employeeGateway = employeeGateway;
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		employeeGateway.deleteById(request.employeeId);
	}

}
