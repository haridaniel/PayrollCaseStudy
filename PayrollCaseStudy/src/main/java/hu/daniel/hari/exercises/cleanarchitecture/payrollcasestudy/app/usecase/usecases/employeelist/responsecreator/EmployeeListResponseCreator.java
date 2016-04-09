package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeVisitorResponseFactory;

public class EmployeeListResponseCreator {

	private LocalDate baseDate;
	private PaymentTypeVisitorResponseFactory paymentTypeVisitorResponseFactory = new PaymentTypeVisitorResponseFactory();
	
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
		response.paymentTypeResponse = employee.getPaymentType().accept(paymentTypeVisitorResponseFactory);
		response.nextPayDay = employee.getPaymentSchedule().getSameOrNextPayDate(baseDate);
		return response;
	}
	
}