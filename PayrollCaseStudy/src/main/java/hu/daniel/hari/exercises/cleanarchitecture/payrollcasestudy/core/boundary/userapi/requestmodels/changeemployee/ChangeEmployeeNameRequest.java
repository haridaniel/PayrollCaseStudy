package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee;

public class ChangeEmployeeNameRequest extends ChangeEmployeeRequest {
	public String newName;

	public ChangeEmployeeNameRequest(int employeeId, String newName) {
		super(employeeId);
		this.newName = newName;
	}
}