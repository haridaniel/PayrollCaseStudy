package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

/**
 * Payday is on every even Friday based on a reference Friday.
 */
public abstract class BiWeeklyPaymentSchedule extends PaymentSchedule {
	public static final LocalDate REFERENCE_DATE_FRIDAY = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY;
	private static final int TWO_WEEK_DAYS_NR = 14;
	
	@Override
	public boolean isPayday(LocalDate date) {
		return isFriday(date) && isOnEvenWeekBasedOnReferenceDate(date);
	}

	@Override
	protected DateInterval getPayIntervalForValidatedPaydate(LocalDate intervalEndDate) throws NotPaydayException {
		LocalDate to = intervalEndDate;
		LocalDate from = to.minusDays(TWO_WEEK_DAYS_NR - 1);
		return DateInterval.of(from, to);
	}
	
	private static boolean isFriday(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.FRIDAY;
	}

	private boolean isOnEvenWeekBasedOnReferenceDate(LocalDate date) {
		return isOnEvenWeekBasedOnEpoch(date) == isOnEvenWeekBasedOnEpoch(REFERENCE_DATE_FRIDAY);
	}

	private static boolean isOnEvenWeekBasedOnEpoch(LocalDate date) {
		long epochDay = date.toEpochDay();
		long epochWeek = epochDay / 7;
		boolean isEven = epochWeek % 2 == 0;
		return isEven;
	}
	
}
