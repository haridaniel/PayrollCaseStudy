package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;

import org.junit.Test;

public class HourlyPaymentClassificationTest {

	private static final LocalDate PREV_SATURDAY = LocalDate.of(2015, 12, 05);
	private static final LocalDate THIS_MONDAY = LocalDate.of(2015, 12, 07);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 11);
	private static final LocalDate NEXT_FRIDAY = LocalDate.of(2015, 12, 18);
	
	@Test
	public void calculatePayForAnyInterval_ShouldNotThrow() throws Exception {
		new HourlyPaymentClassification(0).calculateAmount(new DateInterval(PREV_SATURDAY, THIS_FRIDAY));
		new HourlyPaymentClassification(0).calculateAmount(new DateInterval(PREV_SATURDAY, NEXT_FRIDAY));
	}
}
