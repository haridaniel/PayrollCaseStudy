package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule.NotAPaydayException;

public class WeeklyPaymentScheduleTest {
	private static final LocalDate PREV_SATURDAY = LocalDate.of(2015, 12, 05);
	private static final LocalDate THIS_MONDAY = LocalDate.of(2015, 12, 07);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 11);
	private static final LocalDate NEXT_FRIDAY = LocalDate.of(2015, 12, 18);
	
	private WeeklyPaymentSchedule weeklyPaymentSchedule = mock(WeeklyPaymentSchedule.class, CALLS_REAL_METHODS);
	
	@Test
	public void isPaydayOnFridays_ShouldBeTrue() throws Exception {
		assertThat(weeklyPaymentSchedule.isPayDate(THIS_FRIDAY), is(true));
		assertThat(weeklyPaymentSchedule.isPayDate(NEXT_FRIDAY), is(true));
	}
	
	@Test
	public void isPaydayOnNonFridays_ShouldBeFalse() throws Exception {
		assertThat(weeklyPaymentSchedule.isPayDate(THIS_MONDAY), is(false));
		assertThat(weeklyPaymentSchedule.isPayDate(PREV_SATURDAY), is(false));
	}
	
	@Test
	public void getIntervalOnAFriday() throws Exception {
		DateInterval dateInterval = weeklyPaymentSchedule.getPayInterval(THIS_FRIDAY);
		assertThat(dateInterval.from, 	is(PREV_SATURDAY));
		assertThat(dateInterval.to, 	is(THIS_FRIDAY));
	}
	
	@Test
	public void testGetNextPayday() {
		assertThat(weeklyPaymentSchedule.getSameOrNextPayDate(PREV_SATURDAY), is(THIS_FRIDAY));
		assertThat(weeklyPaymentSchedule.getSameOrNextPayDate(THIS_FRIDAY), is(THIS_FRIDAY));
		assertThat(weeklyPaymentSchedule.getSameOrNextPayDate(THIS_FRIDAY.plusDays(1)), is(NEXT_FRIDAY));
	}


}
