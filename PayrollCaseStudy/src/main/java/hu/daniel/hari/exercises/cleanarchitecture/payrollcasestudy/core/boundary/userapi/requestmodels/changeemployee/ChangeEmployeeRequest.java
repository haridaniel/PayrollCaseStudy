package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request;

public class ChangeEmployeeRequest implements Request {
	public int employeeId;

	public ChangeEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}