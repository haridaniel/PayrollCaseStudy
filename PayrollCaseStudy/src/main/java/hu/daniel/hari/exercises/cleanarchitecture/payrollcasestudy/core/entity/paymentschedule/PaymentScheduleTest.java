package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.NotPaydayException;

public class PaymentScheduleTest {

	private static final LocalDate ANY_DATE = LocalDate.of(2015, 01, 01);

	@Test(expected=NotPaydayException.class)
	public void getIntervalOnNonPayDay_ShouldThrow() throws Exception {
		PaymentSchedule paymentSchedule = mock(PaymentSchedule.class, CALLS_REAL_METHODS);
		when(paymentSchedule.isPayDate(ANY_DATE)).thenReturn(false);
		paymentSchedule.getPayInterval(ANY_DATE);
	}
	@Test
	public void getIntervalOnPayDay_ShouldNotThrow() throws Exception {
		PaymentSchedule paymentSchedule = mock(PaymentSchedule.class, CALLS_REAL_METHODS);
		when(paymentSchedule.isPayDate(ANY_DATE)).thenReturn(true);
		paymentSchedule.getPayInterval(ANY_DATE);
	}

	
}
