package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.formatters.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NextPaydayDateFormatter {
	
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
		return "Today";
	}

	private String toDateFormat(LocalDate date) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
	}
	
}