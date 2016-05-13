package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class DeleteEmployeeUseCase extends TransactionalCommandUseCase<DeleteEmployeeRequest> {

	public DeleteEmployeeUseCase(TransactionalRunner transactionalRunner) {
		super(transactionalRunner);
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		System.out.println("Employee deleted.");
	}

}
