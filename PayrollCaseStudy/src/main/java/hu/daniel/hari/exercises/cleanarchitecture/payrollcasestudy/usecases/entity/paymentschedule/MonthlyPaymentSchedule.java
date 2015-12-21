package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public abstract class MonthlyPaymentSchedule extends PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return isLastDayOfMonth(date);
	}

	@Override
	public DateInterval getPayIntervalForValidatedPaydate(LocalDate payday) {
		return DateInterval.of(
				payday.with(TemporalAdjusters.firstDayOfMonth()),
				payday.with(TemporalAdjusters.lastDayOfMonth()));
	}

	private static boolean isLastDayOfMonth(LocalDate date) {
		return date.with(TemporalAdjusters.lastDayOfMonth()).equals(date);
	}

}
