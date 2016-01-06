package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.ChangeEmployeeRequest;

public abstract class ChangeEmployeeUseCase<T extends ChangeEmployeeRequest> extends TransactionalUseCase<T> {

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