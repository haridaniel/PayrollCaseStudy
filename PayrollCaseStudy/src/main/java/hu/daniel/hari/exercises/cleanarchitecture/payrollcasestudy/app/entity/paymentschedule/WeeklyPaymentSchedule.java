package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class WeeklyPaymentSchedule extends PaymentSchedule {
	private static final DayOfWeek PAYDAY_DAY_OF_WEEK = DayOfWeek.FRIDAY;
	private static final int NR_OF_WEEKDAYS = 7;

	@Override
	public boolean isPayDate(LocalDate date) {
		return isDayOfWeek(date, PAYDAY_DAY_OF_WEEK);
	}
	
	@Override
	public DateInterval getPayIntervalForValidatedPayDate(LocalDate intervalEndDate) {
		return DateInterval.of(getIntervalStartDate(intervalEndDate), intervalEndDate);
	}

	@Override
	public LocalDate getSameOrNextPayDate(LocalDate referenceDate) {
		return getSameOrNextDayOfWeek(referenceDate, PAYDAY_DAY_OF_WEEK);
	}

	private static boolean isDayOfWeek(LocalDate date, DayOfWeek dayOfWeek) {
		return date.getDayOfWeek() == dayOfWeek;
	}

	private static LocalDate getIntervalStartDate(LocalDate intervalEndDate) {
		return intervalEndDate.minusDays(NR_OF_WEEKDAYS - 1);
	}

	private static LocalDate getSameOrNextDayOfWeek(LocalDate referenceDate, DayOfWeek dayOfWeek) {
		return referenceDate.with(TemporalAdjusters.nextOrSame(dayOfWeek));
	}
	
}
