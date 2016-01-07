package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class MonthlyPaymentSchedule extends PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return isLastDayOfMonth(date);
	}

	@Override
	public DateInterval getPayIntervalForValidatedPayDate(LocalDate payday) {
		return DateInterval.of(
				getFirstDayOfMonth(payday),
				getLastDayOfMonth(payday));
	}

	@Override
	public LocalDate getSameOrNextPayDate(LocalDate referenceDate) {
		return getLastDayOfMonth(referenceDate);
	}

	private static boolean isLastDayOfMonth(LocalDate date) {
		return getLastDayOfMonth(date).equals(date);
	}

	private static LocalDate getFirstDayOfMonth(LocalDate date) {
		return date.with(TemporalAdjusters.firstDayOfMonth());
	}

	private static LocalDate getLastDayOfMonth(LocalDate date) {
		return date.with(TemporalAdjusters.lastDayOfMonth());
	}

}
