package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.requestmodels;

import java.time.LocalDate;

public class AddTimeCardRequestModel {
	public int employeeId;
	public LocalDate date;
	public int workingHoursQty;

	public AddTimeCardRequestModel(int employeeId, LocalDate date, int workingHoursQty) {
		this.employeeId = employeeId;
		this.date = date;
		this.workingHoursQty = workingHoursQty;
	}
}
