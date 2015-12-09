package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels;

import java.time.LocalDate;

public class AddSalesReceiptRequestModel {
	public final int employeeId;
	public final LocalDate date;
	public final int amount;
	
	public AddSalesReceiptRequestModel(int employeeId, LocalDate date, int amount) {
		this.employeeId = employeeId;
		this.date = date;
		this.amount = amount;
	}
}
