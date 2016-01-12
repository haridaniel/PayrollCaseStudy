package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeItem;

public class GetEmployeeResponse implements Response {
	public EmployeeItem employeeItem;
	
	public GetEmployeeResponse(EmployeeItem employeeItem) {
		this.employeeItem = employeeItem;
	}

}
