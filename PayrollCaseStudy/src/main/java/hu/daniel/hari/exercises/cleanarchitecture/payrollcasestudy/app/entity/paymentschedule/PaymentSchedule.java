package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;

public abstract class PaymentSchedule {
	
	public abstract boolean isPayDate(LocalDate date);
	
	public final DateInterval getPayInterval(LocalDate payDate) {
		validatePayDate(payDate);
		return getPayIntervalForValidatedPayDate(payDate);
	}
	
	private void validatePayDate(LocalDate date) {
		if (!isPayDate(date))
			throw new NotAPaydayException();
	}

	protected abstract DateInterval getPayIntervalForValidatedPayDate(LocalDate payDate) throws NotAPaydayException;

	public abstract LocalDate getSameOrNextPayDate(LocalDate referenceDate);

	public static class NotAPaydayException extends RuntimeException {
	}

	public static interface PaymentScheduleFactory {
		PaymentSchedule monthlyPaymentSchedule();
		WeeklyPaymentSchedule weeklyPaymentSchedule();
		BiWeeklyPaymentSchedule biWeeklyPaymentSchedule();
	}

	
}
