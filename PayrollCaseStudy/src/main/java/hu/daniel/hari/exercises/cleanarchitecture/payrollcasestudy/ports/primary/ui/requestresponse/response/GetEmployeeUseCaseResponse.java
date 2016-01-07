package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

public class GetEmployeeUseCaseResponse implements Response {
	public EmployeeItem employeeItem;
	
	public GetEmployeeUseCaseResponse(EmployeeItem employeeItem) {
		this.employeeItem = employeeItem;
	}

}
