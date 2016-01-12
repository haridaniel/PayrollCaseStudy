package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class EmployeeListUseCase extends TransactionalEmployeeGatewayUseCase<EmptyRequest> implements HasResponse<EmployeeListResponse> {

	private EmployeeListResponse response;
	
	public EmployeeListUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(EmptyRequest request) {
		response = employeeListResponseCreator().toResponse(employeeGateway.findAll());
	}

	private EmployeeListResponseCreator employeeListResponseCreator() {
		return new EmployeeListResponseCreator(LocalDate.now());
	}

	@Override
	public EmployeeListResponse getResponse() {
		return response;
	}
	
	public static interface ListEmployeesUseCaseFactory {
		EmployeeListUseCase employeeListUseCase();
	}

}

