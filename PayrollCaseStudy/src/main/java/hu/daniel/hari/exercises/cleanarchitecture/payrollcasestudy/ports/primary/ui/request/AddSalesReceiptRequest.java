package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request;

import java.time.LocalDate;

public class AddSalesReceiptRequest implements Request {
	public final int employeeId;
	public final LocalDate date;
	public final int amount;
	
	public AddSalesReceiptRequest(int employeeId, LocalDate date, int amount) {
		this.employeeId = employeeId;
		this.date = date;
		this.amount = amount;
	}
}
