package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

public interface PaymentSchedule {
	
	boolean isPayday(LocalDate date);
	DateInterval getPayIntervalOfPayday(LocalDate paydayDate);
	
}
