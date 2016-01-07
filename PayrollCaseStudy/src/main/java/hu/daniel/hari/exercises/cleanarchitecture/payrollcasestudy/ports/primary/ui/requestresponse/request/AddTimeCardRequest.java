package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request;

import java.time.LocalDate;

public class AddTimeCardRequest implements Request {
	public final int employeeId;
	public final LocalDate date;
	public final int workingHoursQty;

	public AddTimeCardRequest(int employeeId, LocalDate date, int workingHoursQty) {
		this.employeeId = employeeId;
		this.date = date;
		this.workingHoursQty = workingHoursQty;
	}
}
