package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class DeleteEmployeeUseCase extends TransactionalUseCase<DeleteEmployeeRequest> {

	public DeleteEmployeeUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		entityGateway.deleteEmployee(request.employeeId);
	}

}
