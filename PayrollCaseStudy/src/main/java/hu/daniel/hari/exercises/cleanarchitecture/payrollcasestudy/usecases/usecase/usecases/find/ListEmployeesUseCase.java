package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.find;

import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.ListEmployeesUseCaseResponse;

public class ListEmployeesUseCase extends TransactionalUseCase<EmptyRequest> implements HasResponse<ListEmployeesUseCaseResponse> {

	private ListEmployeesUseCaseResponse response;
	private EmployeeItemConverter employeeItemConverter = new EmployeeItemConverter();
	
	public ListEmployeesUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(EmptyRequest request) {
		response = toResponse(employeeGateway.findAll());
	}

	private ListEmployeesUseCaseResponse toResponse(Collection<Employee> employees) {
		ListEmployeesUseCaseResponse response = new ListEmployeesUseCaseResponse();
		response.employeeItems = employees.stream()
			.map(employee -> employeeItemConverter.toEmployeeItem(employee))
			.collect(Collectors.toList());
		return response;
	}

	@Override
	public ListEmployeesUseCaseResponse getResponse() {
		return response;
	}
	
	public static interface ListEmployeesUseCaseFactory {
		ListEmployeesUseCase listEmployeesUseCase();
	}
	
}

