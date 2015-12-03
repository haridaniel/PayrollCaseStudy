package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MontlhyPaymentSchedule implements PaymentSchedule {

	@Override
	public boolean isPayday(LocalDate date) {
		return isLastDayOfMonth(date);
	}
	
	@Override
	public DateInterval getPayIntervalOfPayday(LocalDate payday) {
		assertIsPayday(payday);
		return new DateInterval(
				payday.with(TemporalAdjusters.firstDayOfMonth()), 
				payday.with(TemporalAdjusters.lastDayOfMonth())
				);
	}

	private void assertIsPayday(LocalDate date) {
		if(!isPayday(date))
			throw new RuntimeException("not a payday");
	}

	private static boolean isLastDayOfMonth(LocalDate date) {
		return date.with(TemporalAdjusters.lastDayOfMonth()).equals(date);
	}

}
