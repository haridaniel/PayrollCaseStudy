package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.list;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayFunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse.AffiliationTypeResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class EmployeeListUseCase extends EmployeeGatewayFunctionUseCase<EmployeeListRequest, EmployeeListResponse> {

	private AffiliationTypeResponseFactory affiliationTypeResponseFactory;

	public EmployeeListUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway,
			AffiliationTypeResponseFactory affiliationTypeResponseFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.affiliationTypeResponseFactory = affiliationTypeResponseFactory;
	}

	@Override
	protected EmployeeListResponse executeInTransaction(EmployeeListRequest request) {
		return new EmployeeListResponseCreator(request.currentDate).toResponse(employeeGateway.findAll());
	}

	
	private class EmployeeListResponseCreator {

		private LocalDate baseDate;
		private PaymentTypeResponseFactory paymentTypeResponseFactory = new PaymentTypeResponseFactory();
		
		public EmployeeListResponseCreator(LocalDate baseDate) {
			this.baseDate = baseDate;
		}

		public EmployeeListResponse toResponse(Collection<Employee> employees) {
			return new EmployeeListResponse(
					employees.stream()
					.map(employee -> toResponse(employee))
					.collect(Collectors.toList())
					);
		}

		private EmployeeForEmployeeListResponse toResponse(Employee employee) {
			EmployeeForEmployeeListResponse response = new EmployeeForEmployeeListResponse();
			response.id = employee.getId();
			response.name = employee.getName();
			response.address = employee.getAddress();
			response.paymentTypeResponse = employee.getPaymentType().accept(paymentTypeResponseFactory);
			response.affiliationTypeResponse = affiliationTypeResponseFactory.create(employee.getAffiliation());
			response.nextPayDay = employee.getPaymentSchedule().getSameOrNextPayDate(baseDate);
			return response;
		}
		
	}
}

