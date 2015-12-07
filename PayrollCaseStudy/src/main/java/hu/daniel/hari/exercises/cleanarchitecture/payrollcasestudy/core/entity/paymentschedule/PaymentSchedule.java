package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

public abstract class PaymentSchedule {
	
	public abstract boolean isPayday(LocalDate date);
	
	public final DateInterval getPayInterval(LocalDate payday) {
		validatePayday(payday);
		return getPayIntervalForValidatedPaydate(payday);
	}
	
	private void validatePayday(LocalDate date) {
		if (!isPayday(date))
			throw new NotPaydayException();
	}

	protected abstract DateInterval getPayIntervalForValidatedPaydate(LocalDate payDate) throws NotPaydayException;

	public static class NotPaydayException extends RuntimeException {
	}
	
}
