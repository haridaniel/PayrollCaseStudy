package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import static org.junit.Assert.*;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification.NotFullMonthIntervalException;

import org.junit.Test;

public class SalariedPaymentClassificationTest {


	private static final DateInterval A_MONTH_INTERVAL = 			new DateInterval(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 31));
	private static final DateInterval ANOTHER_MONTH_INTERVAL = 		new DateInterval(LocalDate.of(2015, 11, 01), LocalDate.of(2015, 11, 30));
	private static final DateInterval LESS_THAN_A_MONTH_INTERVAL = 	new DateInterval(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 20));
	private static final DateInterval MORE_THAN_A_MONTH_INTERVAL = 	new DateInterval(LocalDate.of(2015, 11, 30), LocalDate.of(2015, 12, 31));

	@Test
	public void calculatePayForAMonthInterval_ShouldNotThrow() throws Exception {
		new SalariedPaymentClassification(0).calculateAmount(A_MONTH_INTERVAL);
		new SalariedPaymentClassification(0).calculateAmount(ANOTHER_MONTH_INTERVAL);
	}
	
	@Test(expected=NotFullMonthIntervalException.class)
	public void calculatePayForLessThanAMonthInterval_ShouldThrow() throws Exception {
		new SalariedPaymentClassification(0).calculateAmount(LESS_THAN_A_MONTH_INTERVAL);
	}

	@Test(expected=NotFullMonthIntervalException.class)
	public void calculatePayForMoreThanAMonthInterval_ShouldThrow() throws Exception {
		new SalariedPaymentClassification(0).calculateAmount(MORE_THAN_A_MONTH_INTERVAL);
	}
	
}
