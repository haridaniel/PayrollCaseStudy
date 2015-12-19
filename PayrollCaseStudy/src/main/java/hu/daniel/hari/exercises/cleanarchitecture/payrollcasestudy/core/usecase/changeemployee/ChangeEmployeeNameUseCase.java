package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase<ChangeEmployeeNameRequest> {

	public ChangeEmployeeNameUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void change(ChangeEmployeeNameRequest request, Employee employee) {
		employee.setName(request.newName);
	}

}
