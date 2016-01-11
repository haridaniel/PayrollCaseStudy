package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification;

import java.time.Duration;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class CommissionedPaymentClassification extends StrictIntervalPaymentClassification {
	private static final int TWO_WEEK_DAYS = 14;

	public abstract int getBiWeeklyBaseSalary();
	public abstract double getCommissionRate();
	
	public abstract void addSalesReceipt(SalesReceipt salesReceipt);
	public abstract Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval);

	@Override
	public <T> T accept(PaymentClassificationVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	protected int calculateAmountOnValidatedInterval(DateInterval dateInterval) {
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
	
	@Override
	protected boolean isValidInterval(DateInterval dateInterval) {
		return isBiWeeklyInterval(dateInterval);
	}
	
	private boolean isBiWeeklyInterval(DateInterval dateInterval) {
		long daysBetween = Duration.between(dateInterval.from.atStartOfDay(), dateInterval.to.atStartOfDay()).toDays();
		long daysTotal = daysBetween + 1;
		return daysTotal == TWO_WEEK_DAYS;
	}


}
