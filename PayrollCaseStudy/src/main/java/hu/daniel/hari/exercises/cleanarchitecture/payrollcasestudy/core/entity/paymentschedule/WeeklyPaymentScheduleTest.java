package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

import org.junit.Test;

public class WeeklyPaymentScheduleTest {

	private WeeklyPaymentSchedule weeklyPaymentSchedule = new WeeklyPaymentSchedule();

	@Test
	public void testGetIntervalWithFridayShouldBeThisInterval() throws Exception {
		DateInterval dateInterval = weeklyPaymentSchedule.getDateInterval(LocalDate.of(2015, 12, 11));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 12, 05)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 12, 11)));
	}
	@Test
	public void testGetIntervalWithMondayShouldBeThisInterval() throws Exception {
		DateInterval dateInterval = weeklyPaymentSchedule.getDateInterval(LocalDate.of(2015, 12, 07));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 12, 05)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 12, 11)));
	}
	@Test
	public void testGetIntervalWithSaturdayShouldBeNextInterval() throws Exception {
		DateInterval dateInterval = weeklyPaymentSchedule.getDateInterval(LocalDate.of(2015, 12, 12));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 12, 12)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 12, 18)));
	}
	
}
