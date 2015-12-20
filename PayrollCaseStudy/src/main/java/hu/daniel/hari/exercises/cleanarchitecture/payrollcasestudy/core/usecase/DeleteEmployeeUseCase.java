package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.DeleteEmployeeRequest;

public class DeleteEmployeeUseCase extends TransactionalUseCase<DeleteEmployeeRequest> {

	public DeleteEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		employeeGateway.deleteEmployee(request.employeeId);
	}

	public static interface DeleteEmployeeUseCaseFactory {
		DeleteEmployeeUseCase deleteEmployeeUseCase();
	}

}
