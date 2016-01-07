package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import java.util.List;

public class ListEmployeesUseCaseResponse implements Response {
	public List<EmployeeItem> employeeItems;
}
