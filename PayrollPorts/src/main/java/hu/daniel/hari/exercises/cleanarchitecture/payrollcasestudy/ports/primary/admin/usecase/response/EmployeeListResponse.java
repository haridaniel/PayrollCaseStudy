package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response;

import java.time.LocalDate;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.EmployeeBaseForResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.PaymentTypeResponse;

public class EmployeeListResponse implements Response {
	public List<EmployeeForEmployeeListResponse> employees;
	
	public EmployeeListResponse(List<EmployeeForEmployeeListResponse> employees) {
		this.employees = employees;
	}

	public static class EmployeeForEmployeeListResponse extends EmployeeBaseForResponse {
		public PaymentTypeResponse paymentTypeResponse;
		public AffiliationTypeResponse affiliationTypeResponse;
		public LocalDate nextPayDay;
	}
	
}
