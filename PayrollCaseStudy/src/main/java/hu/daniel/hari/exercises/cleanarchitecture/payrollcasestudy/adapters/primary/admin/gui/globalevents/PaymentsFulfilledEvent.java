package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents;

import java.time.LocalDate;

public class PaymentsFulfilledEvent implements EmployeeChangedEvent {
	public LocalDate payDate;
	public int employeeCount;
	public int totalNetAmount;

	public PaymentsFulfilledEvent(LocalDate payDate, int employeeCount, int totalNetAmount) {
		this.payDate = payDate;
		this.employeeCount = employeeCount;
		this.totalNetAmount = totalNetAmount;
	}
}
