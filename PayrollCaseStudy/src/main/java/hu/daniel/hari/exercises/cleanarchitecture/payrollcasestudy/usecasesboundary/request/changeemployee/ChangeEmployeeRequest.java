package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.Request;

public class ChangeEmployeeRequest implements Request {
	public int employeeId;

	public ChangeEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}