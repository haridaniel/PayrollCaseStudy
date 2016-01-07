package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request;

public class DeleteEmployeeRequest implements Request {
	public int employeeId;

	public DeleteEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}