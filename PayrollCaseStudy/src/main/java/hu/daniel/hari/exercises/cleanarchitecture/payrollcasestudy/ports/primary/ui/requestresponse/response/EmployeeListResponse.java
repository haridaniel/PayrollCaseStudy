package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import java.time.LocalDate;
import java.util.List;

public class EmployeeListResponse implements Response {
	public List<EmployeeListItem> employeeListItems;
	
	public EmployeeListResponse(List<EmployeeListItem> employeeListItems) {
		this.employeeListItems = employeeListItems;
	}

	public static class EmployeeListItem extends EmployeeItem {
		public String paymentClassificationTypeString;
		public LocalDate nextPayDay;
	}
	
}
