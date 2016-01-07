package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public abstract class WeeklyPaymentSchedule extends PaymentSchedule {
	private static final int NR_OF_WEEKDAYS = 7;

	@Override
	public boolean isPayDate(LocalDate date) {
		return isFriday(date);
	}
	
	@Override
	public DateInterval getPayIntervalForValidatedPaydate(LocalDate intervalEndDate) {
		LocalDate to = intervalEndDate;
		LocalDate from = to.minusDays(NR_OF_WEEKDAYS - 1);
		return DateInterval.of(from, to);
	}

	private static boolean isFriday(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.FRIDAY;
	}
	
}
