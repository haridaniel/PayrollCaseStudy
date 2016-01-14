package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import java.time.LocalDate;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeBaseForResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse;

public class EmployeeListResponse implements Response {
	public List<EmployeeForEmployeeListResponse> employees;
	
	public EmployeeListResponse(List<EmployeeForEmployeeListResponse> employees) {
		this.employees = employees;
	}

	public static class EmployeeForEmployeeListResponse extends EmployeeBaseForResponse {
		public PaymentTypeResponse paymentTypeResponse;
		public LocalDate nextPayDay;
	}
	
}
