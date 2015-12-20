package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public abstract class ChangeEmployeeUseCase<T extends ChangeEmployeeRequest> extends TransactionalUseCase<T> {

	public ChangeEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected final void executeInTransaction(T request) {
		change(request, employeeGateway.getEmployee(request.employeeId));
	}

	protected abstract void change(T request, Employee employee);
	
	public static interface ChangeEmployeeUseCaseFactory {
		ChangeEmployeeNameUseCase changeEmployeeNameUseCase();
	}
	
}