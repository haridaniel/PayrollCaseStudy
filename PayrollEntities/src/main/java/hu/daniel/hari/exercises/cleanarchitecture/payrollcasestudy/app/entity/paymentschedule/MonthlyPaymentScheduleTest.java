package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule.NotAPaydayException;

import java.time.LocalDate;

import org.junit.Test;

public class MonthlyPaymentScheduleTest {

	private static final LocalDate FIRST_DAY_OF_A_MONTH = 		LocalDate.of(2015, 11, 01);
	private static final LocalDate LAST_DAY_OF_A_MONTH = 		LocalDate.of(2015, 11, 30);
	private static final LocalDate LAST_DAY_OF_NEXT_MONTH = 	LocalDate.of(2015, 12, 31);
	
	private PaymentSchedule monthlyPaymentSchedule = mock(MonthlyPaymentSchedule.class, CALLS_REAL_METHODS);

	@Test
	public void isPaydayOnLastDayOfMonth_ShouldBeTrue() throws Exception {
		assertThat(monthlyPaymentSchedule.isPayDate(LAST_DAY_OF_A_MONTH), is(true));
		assertThat(monthlyPaymentSchedule.isPayDate(LAST_DAY_OF_NEXT_MONTH), is(true));
	}
	
	@Test
	public void isPaydayOnNotLastDayOfMonth_ShouldBeFalse() throws Exception {
		assertThat(monthlyPaymentSchedule.isPayDate(LAST_DAY_OF_A_MONTH.plusDays(1)), is(false));
		assertThat(monthlyPaymentSchedule.isPayDate(LAST_DAY_OF_A_MONTH.minusDays(1)), is(false));
		assertThat(monthlyPaymentSchedule.isPayDate(LAST_DAY_OF_A_MONTH.minusDays(5)), is(false));
	}
	
	@Test
	public void testGetIntervalOnPayday() {
		DateInterval dateInterval = monthlyPaymentSchedule.getPayInterval(LAST_DAY_OF_A_MONTH);
		assertThat(dateInterval.from, 	is(FIRST_DAY_OF_A_MONTH));
		assertThat(dateInterval.to, 	is(LAST_DAY_OF_A_MONTH));
	}

	@Test(expected=NotAPaydayException.class)
	public void getIntervalOnNonPayday_ShouldThrowException() {
		monthlyPaymentSchedule.getPayInterval(LAST_DAY_OF_A_MONTH.plusDays(1));
	}

	@Test
	public void testGetNextPayday() {
		assertThat(monthlyPaymentSchedule.getSameOrNextPayDate(FIRST_DAY_OF_A_MONTH), is(LAST_DAY_OF_A_MONTH));
		assertThat(monthlyPaymentSchedule.getSameOrNextPayDate(LAST_DAY_OF_A_MONTH), is(LAST_DAY_OF_A_MONTH));
		assertThat(monthlyPaymentSchedule.getSameOrNextPayDate(LAST_DAY_OF_A_MONTH.plusDays(1)), is(LAST_DAY_OF_NEXT_MONTH));
	}

	
}
