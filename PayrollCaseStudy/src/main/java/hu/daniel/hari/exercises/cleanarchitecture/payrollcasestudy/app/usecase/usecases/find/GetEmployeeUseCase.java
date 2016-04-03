package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeResponse.EmployeeForGetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetEmployeeUseCase extends TransactionalEmployeeGatewayUseCase<GetEmployeeRequest> implements HasResponse<GetEmployeeResponse> {

	private GetEmployeeResponseCreator getEmployeeResponseCreator = new GetEmployeeResponseCreator();
	private GetEmployeeResponse response;
	
	public GetEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GetEmployeeRequest request) {
		response = getEmployeeResponseCreator.toResponse(employeeGateway.findById(request.employeeId));
	}

	@Override
	public GetEmployeeResponse getResponse() {
		return response;
	}
	
	public static interface GetEmployeeUseCaseFactory extends UseCaseFactory {
		GetEmployeeUseCase getEmployeeUseCase();
	}
	
	private static class GetEmployeeResponseCreator {
		public GetEmployeeResponse toResponse(Employee employee) {
			return new GetEmployeeResponse(to(employee));
		}

		private EmployeeForGetEmployeeResponse to(Employee employee) {
			EmployeeForGetEmployeeResponse response = new EmployeeForGetEmployeeResponse();
			response.id = employee.getId();
			response.name = employee.getName();
			response.address = employee.getAddress();
			return response;
		}
		
	}

}

