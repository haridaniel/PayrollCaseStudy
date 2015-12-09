package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.Duration;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public abstract class CommissionedPaymentClassification extends PaymentClassification {
	private static final int TWO_WEEK_DAYS = 14;

	public abstract int getBiWeeklyBaseSalary();
	public abstract double getCommissionRate();
	
	public abstract void addSalesReceipt(SalesReceipt createSalesReceipt);
	public abstract Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval);

	@Override
	public int calculateAmount(DateInterval dateInterval) {
		validateBiWeekInterval(dateInterval);
		return getBiWeeklyBaseSalary() + calculateCommissionAmount(dateInterval);
	}

	private int calculateCommissionAmount(DateInterval dateInterval) {
		Integer sumAmount = 0;
		Collection<SalesReceipt> salesReceipts = getSalesReceiptsIn(dateInterval);
		for (SalesReceipt salesReceipt : salesReceipts) {
			sumAmount += calculateCommissionAmount(salesReceipt);
		}
		return sumAmount;
	}
	
	private Integer calculateCommissionAmount(SalesReceipt salesReceipt) {
		return (int) (salesReceipt.getAmount() * getCommissionRate());
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
