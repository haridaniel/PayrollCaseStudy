package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;

public abstract class PaymentSchedule {
	
	public abstract boolean isPayDate(LocalDate date);
	
	/**
	 * @throws NotAPaydayException
	 */
	public final DateInterval getPayInterval(LocalDate payDate) {
		return getPayIntervalForValidatedPayDate(validatePayDate(payDate));
	}
	
	private LocalDate validatePayDate(LocalDate date) {
		if (!isPayDate(date))
			throw new NotAPaydayException();
		return date;
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
