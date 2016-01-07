package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

/**
 * Payday is on every even Friday based on a reference Friday.
 */
public abstract class BiWeeklyPaymentSchedule extends PaymentSchedule {
	public static final LocalDate PAYDAY_REFERENCE_DATE = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY;
	private static final DayOfWeek PAYDAY_DAY_OF_WEEK = PAYDAY_REFERENCE_DATE.getDayOfWeek();
	
	@Override
	public boolean isPayDate(LocalDate date) {
		return Util.isDayOfWeek(date, PAYDAY_DAY_OF_WEEK) && Util.isOnEvenWeekBasedOnReferenceDate(date);
	}

	@Override
	protected DateInterval getPayIntervalForValidatedPayDate(LocalDate intervalEndDate) throws NotAPaydayException {
		return DateInterval.of(Util.getIntervalStartDate(intervalEndDate), intervalEndDate);
	}
	
	@Override
	public LocalDate getSameOrNextPayDate(LocalDate referenceDate) {
		LocalDate sameOrNextDayOfWeek = Util.getSameOrNextDayOfWeek(referenceDate, PAYDAY_DAY_OF_WEEK);
		if(Util.isOnEvenWeekBasedOnReferenceDate(sameOrNextDayOfWeek))
			return sameOrNextDayOfWeek;
		else
			return Util.plusWeek(sameOrNextDayOfWeek);
	}

	private static class Util {
		private static final int NR_OF_WEEKDAYS = 7;
		private static final int TWO_WEEK_DAYS_NR = NR_OF_WEEKDAYS * 2;
		
		private static LocalDate getIntervalStartDate(LocalDate intervalEndDate) {
			return intervalEndDate.minusDays(TWO_WEEK_DAYS_NR - 1);
		}
		
		private static boolean isDayOfWeek(LocalDate date, DayOfWeek dayOfWeek) {
			return date.getDayOfWeek() == dayOfWeek;
		}
		
		private static boolean isOnEvenWeekBasedOnReferenceDate(LocalDate date) {
			return isOnEvenWeekBasedOnEpoch(date) == isOnEvenWeekBasedOnEpoch(PAYDAY_REFERENCE_DATE);
		}
		
		private static boolean isOnEvenWeekBasedOnEpoch(LocalDate date) {
			long epochDay = date.toEpochDay();
			long epochWeek = epochDay / 7;
			boolean isEven = epochWeek % 2 == 0;
			return isEven;
		}
		
		private static LocalDate plusWeek(LocalDate sameOrNextDayOfWeek) {
			return sameOrNextDayOfWeek.plusDays(NR_OF_WEEKDAYS);
		}
		
		private static LocalDate getSameOrNextDayOfWeek(LocalDate referenceDate, DayOfWeek dayOfWeek) {
			return referenceDate.with(TemporalAdjusters.nextOrSame(dayOfWeek));
		}
	}
	
}
