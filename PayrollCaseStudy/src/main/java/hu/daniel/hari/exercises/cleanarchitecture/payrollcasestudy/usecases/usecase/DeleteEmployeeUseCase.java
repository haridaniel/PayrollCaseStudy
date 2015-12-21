package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.DeleteEmployeeRequest;

public class DeleteEmployeeUseCase extends TransactionalUseCase<DeleteEmployeeRequest> {

	public DeleteEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		employeeGateway.deleteById(request.employeeId);
	}

	public static interface DeleteEmployeeUseCaseFactory {
		DeleteEmployeeUseCase deleteEmployeeUseCase();
	}

}
