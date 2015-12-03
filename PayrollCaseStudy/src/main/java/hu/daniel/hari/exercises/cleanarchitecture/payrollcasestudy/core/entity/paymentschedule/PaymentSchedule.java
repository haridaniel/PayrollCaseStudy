package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

public interface PaymentSchedule {
	
	boolean isPayday(LocalDate date);
	DateInterval getPayInterval(LocalDate payDate) throws NotPaydayException;
	
	public static class NotPaydayException extends RuntimeException {
	}
	
}
