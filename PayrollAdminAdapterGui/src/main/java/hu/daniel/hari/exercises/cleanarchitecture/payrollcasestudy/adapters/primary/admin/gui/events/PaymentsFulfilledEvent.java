package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events;

import java.time.LocalDate;

public class PaymentsFulfilledEvent implements Event {
	public LocalDate payDate;
	public int employeeCount;
	public int totalNetAmount;

	public PaymentsFulfilledEvent(LocalDate payDate, int employeeCount, int totalNetAmount) {
		this.payDate = payDate;
		this.employeeCount = employeeCount;
		this.totalNetAmount = totalNetAmount;
	}
}
