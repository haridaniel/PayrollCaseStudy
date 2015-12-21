package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request;

public class DeleteEmployeeRequest implements Request {
	public int employeeId;

	public DeleteEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}