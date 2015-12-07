package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import java.time.LocalDate;

public class DateInterval {
	public final LocalDate from;
	public final LocalDate to;

	public DateInterval(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean isBetweenInclusive(LocalDate date) {
		return 	(date.isAfter(from) || date.isEqual(from)) 
					&& 
				(date.isBefore(to) || date.isEqual(to));
	}

	@Override
	public String toString() {
		return "DateInterval [from=" + from + ", to=" + to + "]";
	}

	public static DateInterval of(LocalDate from, LocalDate to) {
		return new DateInterval(from, to);
	}
	
	
}
