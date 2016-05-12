package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.Request;

public class AbstractTimeCardRequest implements Request {
	public final int employeeId;

	public AbstractTimeCardRequest(int employeeId) {
		this.employeeId = employeeId;
	}

}