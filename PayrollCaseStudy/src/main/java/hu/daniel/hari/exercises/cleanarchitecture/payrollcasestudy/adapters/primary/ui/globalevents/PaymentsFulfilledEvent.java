package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents;

import java.time.LocalDate;

public class PaymentsFulfilledEvent implements EmployeeChangedEvent {
	public LocalDate payDate;
	public int employeeCount;
	public int totalGrossAmount;

	public PaymentsFulfilledEvent(LocalDate payDate, int employeeCount, int totalGrossAmount) {
		this.payDate = payDate;
		this.employeeCount = employeeCount;
		this.totalGrossAmount = totalGrossAmount;
	}
}
