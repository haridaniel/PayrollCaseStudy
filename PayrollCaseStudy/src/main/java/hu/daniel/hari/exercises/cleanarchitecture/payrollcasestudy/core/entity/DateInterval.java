package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import java.time.LocalDate;

public class DateInterval {
	public LocalDate from;
	public LocalDate to;

	public DateInterval(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "DateInterval [from=" + from + ", to=" + to + "]";
	}
	
	
}
