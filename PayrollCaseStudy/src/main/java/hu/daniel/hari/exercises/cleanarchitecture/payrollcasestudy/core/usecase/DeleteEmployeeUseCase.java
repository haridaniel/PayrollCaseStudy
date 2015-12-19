package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.DeleteEmployeeRequest;

public class DeleteEmployeeUseCase extends TransactionalUseCase<DeleteEmployeeRequest> {

	public DeleteEmployeeUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		entityGateway.deleteEmployee(request.employeeId);
	}

}
