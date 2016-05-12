package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification;

import java.time.temporal.TemporalAdjusters;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class SalariedPaymentType extends StrictIntervalPaymentType {

	public abstract int getMonthlySalary();
	public abstract void setMonthlySalary(int monthlySalary);

	@Override
	public <T> T accept(PaymentTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	protected int calculateAmountOnValidatedInterval(DateInterval dateInterval) {
		return getMonthlySalary();
	}
	
	@Override
	protected boolean isValidInterval(DateInterval dateInterval) {
		return isFullMonthInterval(dateInterval);
	}

	private static boolean isFullMonthInterval(DateInterval dateInterval) {
		boolean fromIsFirstDayOfMonth = dateInterval.from.equals(dateInterval.from.with(TemporalAdjusters.firstDayOfMonth()));
		boolean toIsLastDayOfMonth = dateInterval.to.equals(dateInterval.to.with(TemporalAdjusters.lastDayOfMonth()));
		return fromIsFirstDayOfMonth && toIsLastDayOfMonth;
	}
	
}
