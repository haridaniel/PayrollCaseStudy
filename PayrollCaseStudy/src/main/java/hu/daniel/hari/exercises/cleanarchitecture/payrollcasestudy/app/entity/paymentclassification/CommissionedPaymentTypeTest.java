package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.StrictIntervalPaymentType.InvalidIntervalException;

public class CommissionedPaymentTypeTest {
	private static final DateInterval A_BIWEEK_INTERVAL = 			DateInterval.of(LocalDate.of(2015, 12, 05), LocalDate.of(2015, 12, 18));
	private static final DateInterval ANOTHER_BIWEEK_INTERVAL = 	DateInterval.of(LocalDate.of(2015, 12, 12), LocalDate.of(2015, 12, 25));
	private static final DateInterval LESS_THAN_A_BIWEEK_INTERVAL = DateInterval.of(LocalDate.of(2015, 12, 05), LocalDate.of(2015, 12, 17));
	private static final DateInterval MORE_THAN_A_BIWEEK_INTERVAL =	DateInterval.of(LocalDate.of(2015, 12, 04), LocalDate.of(2015, 12, 18));

	private static class GivenBase extends CommissionedPaymentType {
		@Override
		public int getBiWeeklyBaseSalary() {
			return 0;
		}
		@Override
		public double getCommissionRate() {
			return 0;
		}
		@Override
		public void addSalesReceipt(SalesReceipt createSalesReceipt) {
		}
		public Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval) {
			return Collections.emptyList();
		}
	}

	private static class GivenBiWeeklyBaseSalary extends GivenBase {
		@Override
		public int getBiWeeklyBaseSalary() {
			return 69_000;
		}
	}

	private static class GivenCommissionRate extends GivenBase {
		@Override
		public double getCommissionRate() {
			return 0.1d;
		}
	}

	private static class GivenOneSalesReceipt extends GivenCommissionRate {
		public Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval) {
			return Arrays.asList(
					salesReceiptMock(10_000));
		}
	}

	private static class GivenTwoSalesReceipts extends GivenCommissionRate {
		public Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval) {
			return Arrays.asList(
					salesReceiptMock(10_000), 
					salesReceiptMock(5_000));
		}
	}

	@Test(expected = InvalidIntervalException.class)
	public void calculatePayForLessThanBiWeeklyInterval_ShouldThrow() throws Exception {
		new GivenBiWeeklyBaseSalary().calculateAmount(LESS_THAN_A_BIWEEK_INTERVAL);
	}

	@Test(expected = InvalidIntervalException.class)
	public void calculatePayForMoreThanAMonthInterval_ShouldThrow() throws Exception {
		new GivenBiWeeklyBaseSalary().calculateAmount(MORE_THAN_A_BIWEEK_INTERVAL);
	}

	@Test
	public void calculatePayForBiWeekInterval_ShouldBeTheSalary() throws Exception {
		assertThat(new GivenBiWeeklyBaseSalary().calculateAmount(A_BIWEEK_INTERVAL), is(69_000));
		assertThat(new GivenBiWeeklyBaseSalary().calculateAmount(ANOTHER_BIWEEK_INTERVAL), is(69_000));
	}

	@Test
	public void calculatePayForBiWeekInterval_WithOneSalesReceipt() throws Exception {
		assertThat(new GivenOneSalesReceipt().calculateAmount(A_BIWEEK_INTERVAL), is(1000));
	}

	@Test
	public void calculatePayForBiWeekInterval_WithTwoSalesReceipt() throws Exception {
		assertThat(new GivenTwoSalesReceipts().calculateAmount(A_BIWEEK_INTERVAL), is(1500));
	}

	private static SalesReceipt salesReceiptMock(int amount) {
		SalesReceipt salesReceipt = mock(SalesReceipt.class);
		when(salesReceipt.getAmount()).thenReturn(amount);
		return salesReceipt;
	}

}
