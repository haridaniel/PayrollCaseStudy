package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MontlhyPaymentSchedule implements PaymentSchedule {

	@Override
	public boolean isPayday(LocalDate date) {
		return isLastDayOfMonth(date);
	}

	private static boolean isLastDayOfMonth(LocalDate date) {
		return date.with(TemporalAdjusters.lastDayOfMonth()).equals(date);
	}

	@Override
	public DateInterval getPayInterval(LocalDate payday) {
		validatePayday(payday);
		return new DateInterval(
				payday.with(TemporalAdjusters.firstDayOfMonth()),
				payday.with(TemporalAdjusters.lastDayOfMonth()));
	}

	private void validatePayday(LocalDate date) {
		if (!isPayday(date))
			throw new NotPaydayException();
	}

}
