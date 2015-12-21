package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.PaymentSchedule.NotPaydayException;

import java.time.LocalDate;

import org.junit.Test;

public class MontlhyPaymentScheduleTest {

	MontlhyPaymentSchedule montlhyPaymentSchedule = new MonthlyPaymentScheduleImpl();

	@Test
	public void isPaydayOnLastDayOfMonth_ShouldBeTrue() throws Exception {
		assertThat(montlhyPaymentSchedule.isPayDate(LocalDate.of(2015, 12, 31)), is(true));
		assertThat(montlhyPaymentSchedule.isPayDate(LocalDate.of(2015, 11, 30)), is(true));
	}
	@Test
	public void isPaydayOnNotLastDayOfMonth_ShouldBeFalse() throws Exception {
		assertThat(montlhyPaymentSchedule.isPayDate(LocalDate.of(2015, 11, 24)), is(false));
		assertThat(montlhyPaymentSchedule.isPayDate(LocalDate.of(2015, 12, 01)), is(false));
		assertThat(montlhyPaymentSchedule.isPayDate(LocalDate.of(2015, 12, 02)), is(false));
	}
	
	@Test
	public void getIntervalOnPayday() {
		DateInterval dateInterval = montlhyPaymentSchedule.getPayInterval(LocalDate.of(2015, 12, 31));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 12, 01)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 12, 31)));
	}

	@Test(expected=NotPaydayException.class)
	public void getIntervalOnNonPayday_ShouldThrowException() {
		montlhyPaymentSchedule.getPayInterval(LocalDate.of(2015, 11, 1));
	}
	
}
