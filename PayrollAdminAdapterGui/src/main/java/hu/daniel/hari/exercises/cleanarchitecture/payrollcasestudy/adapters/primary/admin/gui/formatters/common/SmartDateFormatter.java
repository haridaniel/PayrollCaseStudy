package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.Constants;

public class SmartDateFormatter {
	
	private static final String TODAY = "Today";
	private String dateFormat = Constants.DATE_FORMAT;
	private LocalDate currentDate;

	public SmartDateFormatter() {
		this(LocalDate.now());
	}
	public SmartDateFormatter(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public String format(LocalDate date) {
		if(date.isEqual(currentDate))
			return TODAY;
		return toDateFormat(date);
	}

	private String toDateFormat(LocalDate date) {
		return DateTimeFormatter.ofPattern(dateFormat).format(date);
	}
	
}