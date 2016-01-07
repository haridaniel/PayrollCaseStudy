package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee;

public class ChangeEmployeeNameRequest extends ChangeEmployeeRequest {
	public String newName;

	public ChangeEmployeeNameRequest(int employeeId, String newName) {
		super(employeeId);
		this.newName = newName;
	}
}