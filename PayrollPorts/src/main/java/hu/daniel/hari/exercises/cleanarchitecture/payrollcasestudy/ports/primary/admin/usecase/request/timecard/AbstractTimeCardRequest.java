package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.timecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.Request;

public class AbstractTimeCardRequest implements Request {
	public final int employeeId;

	public AbstractTimeCardRequest(int employeeId) {
		this.employeeId = employeeId;
	}

}