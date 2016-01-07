package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetEmployeeUseCase extends TransactionalEmployeeGatewayUseCase<GetEmployeeRequest> implements HasResponse<GetEmployeeResponse> {

	private GetEmployeeResponse response;
	private EmployeeItemConverter employeeItemConverter = new EmployeeItemConverter();
	
	public GetEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GetEmployeeRequest request) {
		response = toResponse(employeeGateway.findById(request.employeeId));
	}

	private GetEmployeeResponse toResponse(Employee employee) {
		return new GetEmployeeResponse(employeeItemConverter.toEmployeeItem(employee));
	}

	@Override
	public GetEmployeeResponse getResponse() {
		return response;
	}
	
	public static interface GetEmployeeUseCaseFactory {
		GetEmployeeUseCase getEmployeeUseCase();
	}

}

