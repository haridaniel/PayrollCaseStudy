package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public class BiWeeklyPaymentScheduleTest {
	private static final LocalDate REFERENCE_EVEN_FRIDAY = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY;
	private static final LocalDate ODD_FRIDAY = REFERENCE_EVEN_FRIDAY.plusDays(7);
	private static final LocalDate NEXT_EVEN_FRIDAY = REFERENCE_EVEN_FRIDAY.plusDays(14);
	private static final LocalDate NEXT_ODD_FRIDAY = REFERENCE_EVEN_FRIDAY.plusDays(21);

	private BiWeeklyPaymentSchedule biWeeklyPaymentSchedule = mock(BiWeeklyPaymentSchedule.class, CALLS_REAL_METHODS);
	
	@Test
	public void isPaydayOnNotFridays_ShouldBeFalse() throws Exception {
		assertThat(biWeeklyPaymentSchedule.isPayday(ODD_FRIDAY.plusDays(1)), is(false));
		assertThat(biWeeklyPaymentSchedule.isPayday(ODD_FRIDAY.minusDays(1)), is(false));
	}
	
	@Test
	public void isPaydayOnEvenFridays_ShouldBeTrue() throws Exception {
		assertThat(biWeeklyPaymentSchedule.isPayday(REFERENCE_EVEN_FRIDAY), is(true));
		assertThat(biWeeklyPaymentSchedule.isPayday(NEXT_EVEN_FRIDAY), is(true));
	}
	
	@Test
	public void isPaydayOnOddFridays_ShouldBeFalse() throws Exception {
		assertThat(biWeeklyPaymentSchedule.isPayday(ODD_FRIDAY), is(false));
		assertThat(biWeeklyPaymentSchedule.isPayday(NEXT_ODD_FRIDAY), is(false));
	}
	
	@Test
	public void getIntervalOnAnEvenFriday() throws Exception {
		DateInterval dateInterval = biWeeklyPaymentSchedule.getPayInterval(NEXT_EVEN_FRIDAY);
		assertThat(dateInterval.from, 	is(REFERENCE_EVEN_FRIDAY.plusDays(1)));
		assertThat(dateInterval.to, 	is(NEXT_EVEN_FRIDAY));
	}
	
}
