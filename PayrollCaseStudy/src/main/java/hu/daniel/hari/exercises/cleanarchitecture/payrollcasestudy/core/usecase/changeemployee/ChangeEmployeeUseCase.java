package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public abstract class ChangeEmployeeUseCase<T extends ChangeEmployeeRequest> extends TransactionalUseCase<T> {

	public ChangeEmployeeUseCase(Database database) {
		super(database);
	}

	@Override
	protected final void executeInTransaction(T request) {
		change(request, entityGateway.getEmployee(request.employeeId));
	}

	protected abstract void change(T request, Employee employee);
	
}