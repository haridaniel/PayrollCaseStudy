package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;

public class ChangeEmployeeRequest implements Request {
	public int employeeId;

	public ChangeEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}