package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

public class GetEmployeeResponse implements Response {
	public EmployeeItem employeeItem;
	
	public GetEmployeeResponse(EmployeeItem employeeItem) {
		this.employeeItem = employeeItem;
	}

}
