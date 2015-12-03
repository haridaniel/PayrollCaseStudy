package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class WeeklyPaymentSchedule implements PaymentSchedule {

	@Override
	public DateInterval getDateInterval(LocalDate dateInInterval) {
		//PREV FRIDAY + 1
		LocalDate from = dateInInterval.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).plusDays(1);
		//THIS FRIDAY
		LocalDate to = from.plusDays(6);
		return new DateInterval(from, to);
	}

}
