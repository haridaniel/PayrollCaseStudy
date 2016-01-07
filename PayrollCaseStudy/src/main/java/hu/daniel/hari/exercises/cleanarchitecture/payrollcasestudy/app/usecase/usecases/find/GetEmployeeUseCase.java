package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeUseCaseRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetEmployeeUseCase extends TransactionalUseCase<GetEmployeeUseCaseRequest> implements HasResponse<GetEmployeeUseCaseResponse> {

	private GetEmployeeUseCaseResponse response;
	private EmployeeItemConverter employeeItemConverter = new EmployeeItemConverter();
	
	public GetEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GetEmployeeUseCaseRequest request) {
		response = toResponse(employeeGateway.findById(request.employeeId));
	}

	private GetEmployeeUseCaseResponse toResponse(Employee employee) {
		return new GetEmployeeUseCaseResponse(employeeItemConverter.toEmployeeItem(employee));
	}

	@Override
	public GetEmployeeUseCaseResponse getResponse() {
		return response;
	}
	
	public static interface GetEmployeeUseCaseFactory {
		GetEmployeeUseCase getEmployeeUseCase();
	}

}

