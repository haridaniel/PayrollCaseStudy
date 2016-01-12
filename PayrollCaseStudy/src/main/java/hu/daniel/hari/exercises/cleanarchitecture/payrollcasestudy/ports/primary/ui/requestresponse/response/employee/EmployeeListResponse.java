package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee;

import java.time.LocalDate;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.Response;

public class EmployeeListResponse implements Response {
	public List<EmployeeListItem> employeeListItems;
	
	public EmployeeListResponse(List<EmployeeListItem> employeeListItems) {
		this.employeeListItems = employeeListItems;
	}

	public static class EmployeeListItem extends EmployeeItem {
		public LocalDate nextPayDay;
	}
	
}
