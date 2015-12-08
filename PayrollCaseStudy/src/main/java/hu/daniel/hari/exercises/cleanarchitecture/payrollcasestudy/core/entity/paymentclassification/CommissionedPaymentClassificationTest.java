package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification.NotBiWeeklyIntervalException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification.NotFullMonthIntervalException;

import org.junit.Test;

public class CommissionedPaymentClassificationTest {
	private static final DateInterval A_BIWEEK_INTERVAL = 			DateInterval.of(LocalDate.of(2015, 12, 05), LocalDate.of(2015, 12, 18));
	private static final DateInterval ANOTHER_BIWEEK_INTERVAL = 	DateInterval.of(LocalDate.of(2015, 12, 12), LocalDate.of(2015, 12, 25));
	private static final DateInterval LESS_THAN_A_BIWEEK_INTERVAL = DateInterval.of(LocalDate.of(2015, 12, 05), LocalDate.of(2015, 12, 17));
	private static final DateInterval MORE_THAN_A_BIWEEK_INTERVAL =	DateInterval.of(LocalDate.of(2015, 12, 04), LocalDate.of(2015, 12, 18));

	CommissionedPaymentClassification commissionedPaymentClassification = new CommissionedPaymentClassification() {
		@Override
		public int getBiWeeklyBaseSalary() {
			return 69_000;
		}
		@Override
		public double getCommissionRate() {
			return 0;
		}
	};
	
	@Test
	public void calculatePayForBiWeekInterval_ShouldBeTheSalary() throws Exception {
		assertThat(commissionedPaymentClassification.calculateAmount(A_BIWEEK_INTERVAL), is(69_000));
		assertThat(commissionedPaymentClassification.calculateAmount(ANOTHER_BIWEEK_INTERVAL), is(69_000));
	}
	
	@Test(expected=NotBiWeeklyIntervalException.class)
	public void calculatePayForLessThanBiWeeklyInterval_ShouldThrow() throws Exception {
		commissionedPaymentClassification.calculateAmount(LESS_THAN_A_BIWEEK_INTERVAL);
	}

	@Test(expected=NotBiWeeklyIntervalException.class)
	public void calculatePayForMoreThanAMonthInterval_ShouldThrow() throws Exception {
		commissionedPaymentClassification.calculateAmount(MORE_THAN_A_BIWEEK_INTERVAL);
	}
	
	
}
