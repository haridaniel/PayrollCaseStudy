package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response;

import java.time.LocalDate;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.AffiliationTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.EmployeeBaseForResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.paymenttype.PaymentTypeResponse;

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
