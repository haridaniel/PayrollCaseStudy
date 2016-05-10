package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request;

public class GetEmployeeRequest implements Request {
	public int employeeId;

	public GetEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}