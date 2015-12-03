package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class SalariedPaymentClassification extends PaymentClassification {

	private int monthlySalary;

	public SalariedPaymentClassification(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public int calculateAmount(DateInterval dateInterval) {
		validateFullMonthInterval(dateInterval);
		return monthlySalary;
	}

	private static void validateFullMonthInterval(DateInterval dateInterval) {
		if (!isFullMonthInterval(dateInterval))
			throw new NotFullMonthIntervalException();
	}

	private static boolean isFullMonthInterval(DateInterval dateInterval) {
		boolean fromIsFirstDayOfMonth = dateInterval.from.equals(dateInterval.from.with(TemporalAdjusters.firstDayOfMonth()));
		boolean toIsLastDayOfMonth = dateInterval.to.equals(dateInterval.to.with(TemporalAdjusters.lastDayOfMonth()));
		return fromIsFirstDayOfMonth && toIsLastDayOfMonth;
	}
	
	public static class NotFullMonthIntervalException extends RuntimeException {}

}
