package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.Constants;

public class SmartDateFormatter {
	
	private static final String TODAY = "Today";
	private String dateFormat = Constants.DATE_FORMAT;
	private LocalDate currentDate;

	public SmartDateFormatter(@Assisted LocalDate currentDate) {
		this.currentDate = currentDate;
	}
	
	public SmartDateFormatter() {
		this(LocalDate.now());
	}

	public String format(LocalDate date) {
		if(date.isEqual(currentDate))
			return TODAY;
		return toDateFormat(date);
	}

	private String toDateFormat(LocalDate date) {
		return DateTimeFormatter.ofPattern(dateFormat).format(date);
	}
	
	public static interface SmartDateFormatterFactory {
		SmartDateFormatter of(LocalDate currentDate);
		SmartDateFormatter ofCurrentDate();
	}
	
}