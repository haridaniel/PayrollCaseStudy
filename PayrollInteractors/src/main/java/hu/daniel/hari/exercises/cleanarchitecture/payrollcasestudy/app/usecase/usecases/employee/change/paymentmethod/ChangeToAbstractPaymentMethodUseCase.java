package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.change.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.change.ChangeEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class ChangeToAbstractPaymentMethodUseCase<T extends ChangeEmployeeRequest> extends ChangeEmployeeUseCase<T> {

	public ChangeToAbstractPaymentMethodUseCase(
			TransactionalRunner transactionalRunner,
			EmployeeGateway employeeGateway 
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void change(Employee employee, T request) {
		employee.setPaymentMethod(getPaymentMethod(request));
	}

	protected abstract PaymentMethod getPaymentMethod(T request);

	
}