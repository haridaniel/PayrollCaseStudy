package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import java.util.List;

public class ListEmployeesResponse implements Response {
	public List<EmployeeItem> employeeItems;
}
