package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels;

public class DeleteEmployeeRequest implements Request {
	public int employeeId;

	public DeleteEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}