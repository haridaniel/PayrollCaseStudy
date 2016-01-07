package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request;

public class GetEmployeeUseCaseRequest implements Request {
	public int employeeId;

	public GetEmployeeUseCaseRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}