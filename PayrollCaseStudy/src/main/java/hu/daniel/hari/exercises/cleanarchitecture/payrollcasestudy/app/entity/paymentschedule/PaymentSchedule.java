package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;

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

	public static interface PaymentScheduleFactory {
		MonthlyPaymentSchedule monthlyPaymentSchedule();
		WeeklyPaymentSchedule weeklyPaymentSchedule();
		BiWeeklyPaymentSchedule biWeeklyPaymentSchedule();
	}

	
}
