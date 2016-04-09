package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NextPaydayDateFormatter {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TODAY = "Today";
	private LocalDate currentDate;

	public NextPaydayDateFormatter(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public String format(LocalDate date) {
		if(date.isEqual(currentDate))
			return today();
		return toDateFormat(date);
	}

	private String today() {
		return TODAY;
	}

	private String toDateFormat(LocalDate date) {
		return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
	}
	
}