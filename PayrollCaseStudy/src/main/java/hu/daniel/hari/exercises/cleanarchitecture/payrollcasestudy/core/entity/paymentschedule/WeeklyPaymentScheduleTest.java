package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.NotPaydayException;

import java.time.LocalDate;

import org.junit.Test;

public class WeeklyPaymentScheduleTest {
	private static final LocalDate PREV_SATURDAY = LocalDate.of(2015, 12, 05);
	private static final LocalDate THIS_MONDAY = LocalDate.of(2015, 12, 07);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 11);
	private static final LocalDate NEXT_FRIDAY = LocalDate.of(2015, 12, 18);
	
	private WeeklyPaymentSchedule weeklyPaymentSchedule = mock(WeeklyPaymentSchedule.class, CALLS_REAL_METHODS);
	
	@Test
	public void isPaydayOnFridays_ShouldBeTrue() throws Exception {
		assertThat(weeklyPaymentSchedule.isPayday(THIS_FRIDAY), is(true));
		assertThat(weeklyPaymentSchedule.isPayday(NEXT_FRIDAY), is(true));
	}
	
	@Test
	public void isPaydayOnNonFridays_ShouldBeFalse() throws Exception {
		assertThat(weeklyPaymentSchedule.isPayday(THIS_MONDAY), is(false));
		assertThat(weeklyPaymentSchedule.isPayday(PREV_SATURDAY), is(false));
	}
	
	@Test
	public void getIntervalOnAFriday() throws Exception {
		DateInterval dateInterval = weeklyPaymentSchedule.getPayInterval(THIS_FRIDAY);
		assertThat(dateInterval.from, 	is(PREV_SATURDAY));
		assertThat(dateInterval.to, 	is(THIS_FRIDAY));
	}

}
