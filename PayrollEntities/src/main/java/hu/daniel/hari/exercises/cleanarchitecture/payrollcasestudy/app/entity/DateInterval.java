package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateInterval {
	public final LocalDate from;
	public final LocalDate to;

	private DateInterval(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean isBetweenInclusive(LocalDate date) {
		return 	(date.isAfter(from) || date.isEqual(from)) 
					&& 
				(date.isBefore(to) || date.isEqual(to));
	}
	
	//NOT efficient
	public int countWeekDayInclusive(DayOfWeek dayOfWeek) {
		int count = 0; 
		for (LocalDate date = from; date.isBefore(to) || date.isEqual(to); date = date.plusDays(1)) {
			if(date.getDayOfWeek().equals(dayOfWeek))
				count++;
		}
		return count;
	}

	@Override
	public String toString() {
		return "DateInterval [from=" + from + ", to=" + to + "]";
	}

	public static DateInterval of(LocalDate from, LocalDate to) {
		return new DateInterval(from, to);
	}

	public static DateInterval ofSingleDate(LocalDate fromAndTo) {
		return of(fromAndTo, fromAndTo);
	}
	
}
