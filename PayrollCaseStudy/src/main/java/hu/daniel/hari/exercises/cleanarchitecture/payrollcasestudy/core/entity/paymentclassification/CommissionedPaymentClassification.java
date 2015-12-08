package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.Duration;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public abstract class CommissionedPaymentClassification extends PaymentClassification {
	private static final int TWO_WEEK_DAYS = 14;

	public abstract int getBiWeeklyBaseSalary();
	public abstract double getCommissionRate();

	@Override
	public int calculateAmount(DateInterval dateInterval) {
		validateBiWeekInterval(dateInterval);
		return getBiWeeklyBaseSalary();
	}

	private void validateBiWeekInterval(DateInterval dateInterval) {
		if (!isBiWeekInterval(dateInterval))
			throw new NotBiWeeklyIntervalException();
	}

	private boolean isBiWeekInterval(DateInterval dateInterval) {
		long daysBetween = Duration.between(dateInterval.from.atStartOfDay(), dateInterval.to.atStartOfDay()).toDays();
		long daysTotal = daysBetween + 1;
		return daysTotal == TWO_WEEK_DAYS;
	}

	public static class NotBiWeeklyIntervalException extends RuntimeException {}

}
