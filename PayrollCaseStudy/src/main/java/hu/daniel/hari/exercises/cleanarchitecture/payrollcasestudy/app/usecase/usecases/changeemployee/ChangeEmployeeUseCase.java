package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class ChangeEmployeeUseCase<T extends ChangeEmployeeRequest> extends TransactionalEmployeeGatewayUseCase<T> {

	public ChangeEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected final void executeInTransaction(T request) {
		change(employeeGateway.findById(request.employeeId), request);
	}

	protected abstract void change(Employee employee, T request);
	
	public static interface ChangeEmployeeUseCaseFactory {
		ChangeEmployeeNameUseCase changeEmployeeNameUseCase();
	}
	
}