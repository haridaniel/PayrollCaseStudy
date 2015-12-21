package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public abstract class StrictIntervalPaymentClassification extends PaymentClassification {
	
	@Override
	public final int calculateAmount(DateInterval dateInterval) {
		validate(dateInterval);
		return calculateAmountOnValidatedInterval(dateInterval);
	}
	
	private void validate(DateInterval dateInterval) {
		if (!isValidInterval(dateInterval))
			throw new InvalidIntervalException();
	}

	protected abstract boolean isValidInterval(DateInterval dateInterval);
	
	protected abstract int calculateAmountOnValidatedInterval(DateInterval dateInterval);

	public static class InvalidIntervalException extends RuntimeException {}

}