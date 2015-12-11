package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

public abstract class PaymentSchedule {
	
	public abstract boolean isPayDate(LocalDate date);
	
	public final DateInterval getPayInterval(LocalDate payDate) {
		validatePayDate(payDate);
		return getPayIntervalForValidatedPaydate(payDate);
	}
	
	private void validatePayDate(LocalDate date) {
		if (!isPayDate(date))
			throw new NotPaydayException();
	}

	protected abstract DateInterval getPayIntervalForValidatedPaydate(LocalDate payDate) throws NotPaydayException;

	public static class NotPaydayException extends RuntimeException {
	}
	
}
