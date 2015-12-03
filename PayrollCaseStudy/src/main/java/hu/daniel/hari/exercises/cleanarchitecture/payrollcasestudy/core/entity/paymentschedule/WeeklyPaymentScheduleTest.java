package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.NotPaydayException;

import java.time.LocalDate;

import org.junit.Test;

public class WeeklyPaymentScheduleTest {
	private static final LocalDate PREV_SATURDAY = LocalDate.of(2015, 12, 05);
	private static final LocalDate THIS_MONDAY = LocalDate.of(2015, 12, 07);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 11);
	private static final LocalDate NEXT_FRIDAY = LocalDate.of(2015, 12, 18);
	
	private WeeklyPaymentSchedule weeklyPaymentSchedule = new WeeklyPaymentSchedule();
	
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
	public void getIntervalWithFriday() throws Exception {
		DateInterval dateInterval = weeklyPaymentSchedule.getPayInterval(THIS_FRIDAY);
		assertThat(dateInterval.from, 	is(PREV_SATURDAY));
		assertThat(dateInterval.to, 	is(THIS_FRIDAY));
	}

	@Test(expected=NotPaydayException.class)
	public void getIntervalWithNonFriday_ShouldThrow() throws Exception {
		weeklyPaymentSchedule.getPayInterval(THIS_MONDAY);
	}
	
}
