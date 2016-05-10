package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase<ChangeEmployeeNameRequest> {

	public ChangeEmployeeNameUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void change(Employee employee, ChangeEmployeeNameRequest request) {
		employee.setName(request.newName);
	}

}
