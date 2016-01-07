package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request;

public class GetEmployeeUseCaseRequest implements Request {
	public int employeeId;

	public GetEmployeeUseCaseRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}