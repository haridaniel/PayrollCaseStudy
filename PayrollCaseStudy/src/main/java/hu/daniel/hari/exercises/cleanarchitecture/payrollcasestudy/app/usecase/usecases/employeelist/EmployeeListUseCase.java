package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class EmployeeListUseCase extends TransactionalEmployeeGatewayUseCase<EmployeeListRequest> implements HasResponse<EmployeeListResponse> {

	private EmployeeListResponse response;
	
	public EmployeeListUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(EmployeeListRequest request) {
		response = new EmployeeListResponseCreator(request.currentDate).toResponse(employeeGateway.findAll());
	}

	@Override
	public EmployeeListResponse getResponse() {
		return response;
	}
	
	public static interface ListEmployeesUseCaseFactory {
		EmployeeListUseCase employeeListUseCase();
	}

}

