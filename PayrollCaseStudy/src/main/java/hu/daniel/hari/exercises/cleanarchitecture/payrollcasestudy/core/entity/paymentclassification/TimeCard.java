package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.LocalDate;

public class TimeCard {
	public LocalDate date;
	public int workingHourQty;

	public TimeCard(LocalDate date, int workingHourQty) {
		this.date = date;
		this.workingHourQty = workingHourQty;
	}
	
}
