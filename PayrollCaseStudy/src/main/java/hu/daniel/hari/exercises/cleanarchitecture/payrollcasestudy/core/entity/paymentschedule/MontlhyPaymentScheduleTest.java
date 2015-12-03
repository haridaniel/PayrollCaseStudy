package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

import org.junit.Test;

public class MontlhyPaymentScheduleTest {

	MontlhyPaymentSchedule montlhyPaymentSchedule = new MontlhyPaymentSchedule();

	@Test
	public void testGetPeriodWithMonthEndDateShouldBeThisMonth() {
		DateInterval dateInterval = montlhyPaymentSchedule.getDateInterval(LocalDate.of(2015, 12, 31));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 12, 01)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 12, 31)));
	}

	@Test
	public void testGetPeriodWithMonthStartDateShouldBeThisMonth() {
		DateInterval dateInterval = montlhyPaymentSchedule.getDateInterval(LocalDate.of(2015, 11, 1));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 11, 01)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 11, 30)));
	}
	
}
