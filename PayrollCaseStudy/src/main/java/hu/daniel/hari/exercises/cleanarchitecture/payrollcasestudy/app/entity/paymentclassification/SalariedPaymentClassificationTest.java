package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.StrictIntervalPaymentClassification.InvalidIntervalException;

public class SalariedPaymentClassificationTest {
	private static final DateInterval A_MONTH_INTERVAL = 			DateInterval.of(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 31));
	private static final DateInterval ANOTHER_MONTH_INTERVAL = 		DateInterval.of(LocalDate.of(2015, 11, 01), LocalDate.of(2015, 11, 30));
	private static final DateInterval LESS_THAN_A_MONTH_INTERVAL = 	DateInterval.of(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 20));
	private static final DateInterval MORE_THAN_A_MONTH_INTERVAL = 	DateInterval.of(LocalDate.of(2015, 11, 30), LocalDate.of(2015, 12, 31));

	private SalariedPaymentClassification salariedPaymentClassification = new SalariedPaymentClassification() {
		@Override
		public void setMonthlySalary(int monthlySalary) {
		}
		@Override
		public int getMonthlySalary() {
			return 1000;
		}
	};
	
	@Test
	public void calculatePayForAMonthInterval_ShouldBeTheSalary() throws Exception {
		assertThat(salariedPaymentClassification.calculateAmount(A_MONTH_INTERVAL), is(1000));
		assertThat(salariedPaymentClassification.calculateAmount(ANOTHER_MONTH_INTERVAL), is(1000));
	}
	
	@Test(expected=InvalidIntervalException.class)
	public void calculatePayForLessThanAMonthInterval_ShouldThrow() throws Exception {
		salariedPaymentClassification.calculateAmount(LESS_THAN_A_MONTH_INTERVAL);
	}

	@Test(expected=InvalidIntervalException.class)
	public void calculatePayForMoreThanAMonthInterval_ShouldThrow() throws Exception {
		salariedPaymentClassification.calculateAmount(MORE_THAN_A_MONTH_INTERVAL);
	}
	
	
	
}
