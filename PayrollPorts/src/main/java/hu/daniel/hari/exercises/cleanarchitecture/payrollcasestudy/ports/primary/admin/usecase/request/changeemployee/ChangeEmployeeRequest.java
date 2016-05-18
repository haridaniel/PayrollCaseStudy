package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.Request;

public class ChangeEmployeeRequest implements Request {
	public int employeeId;

	public ChangeEmployeeRequest(int employeeId) {
		this.employeeId = employeeId;
	}
}