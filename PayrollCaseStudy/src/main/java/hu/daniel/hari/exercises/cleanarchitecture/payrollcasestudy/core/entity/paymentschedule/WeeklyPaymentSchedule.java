package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.DayOfWeek;
import java.time.LocalDate;

public abstract class WeeklyPaymentSchedule implements PaymentSchedule {
	private static final int NR_OF_WEEKDAYS = 7;

	@Override
	public boolean isPayday(LocalDate date) {
		return isFriday(date);
	}

	@Override
	public DateInterval getPayInterval(LocalDate payday) {
		validatePayday(payday);
		LocalDate to = payday;
		LocalDate from = to.minusDays(NR_OF_WEEKDAYS - 1);
		return new DateInterval(from, to);
	}

	private void validatePayday(LocalDate payday) {
		if (!isPayday(payday))
			throw new NotPaydayException();
	}

	private static boolean isFriday(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.FRIDAY;
	}

}
