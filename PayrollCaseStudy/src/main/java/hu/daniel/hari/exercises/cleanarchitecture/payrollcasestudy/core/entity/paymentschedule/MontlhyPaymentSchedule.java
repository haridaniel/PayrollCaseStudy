package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MontlhyPaymentSchedule implements PaymentSchedule {

	@Override
	public DateInterval getDateInterval(LocalDate dateInInterval) {
		return new DateInterval(
				dateInInterval.with(TemporalAdjusters.firstDayOfMonth()), 
				dateInInterval.with(TemporalAdjusters.lastDayOfMonth())
				);
	}

}
