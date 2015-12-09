package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HourlyPaymentClassificationTest {

	private static final DateInterval ANY_ITERVAL = null;

	@Test
	public void calculatePayForOneTimeCard_ForNormalHours() throws Exception {
		HourlyPaymentClassification hourlyPaymentClassification = hourlyPaymentClassificationWithOneTimeCard(10, 8);
		assertThat(hourlyPaymentClassification.calculateAmount(ANY_ITERVAL), is(80));
	}

	@Test
	public void calculatePayForMultipleTimeCard_ForNormalHours() throws Exception {
		HourlyPaymentClassification hourlyPaymentClassification = hourlyPaymentClassificationWithMultipleTimeCard(10, Arrays.asList(8, 5, 7));
		assertThat(hourlyPaymentClassification.calculateAmount(ANY_ITERVAL), is((8 + 5 + 7) * 10));
	}

	@Test
	public void calculatePayForOneTimeCard_ForOvertimeHours() throws Exception {
		HourlyPaymentClassification hourlyPaymentClassification = hourlyPaymentClassificationWithOneTimeCard(10, 10);
		int calculateAmount = hourlyPaymentClassification.calculateAmount(ANY_ITERVAL);
		int standard = 10 * 8;
		int overtime = (int) (10d * 1.5 * 2);
		assertThat(calculateAmount, is(standard + overtime));
	}

	private HourlyPaymentClassification hourlyPaymentClassificationWithOneTimeCard(int hourlyWage, int workingHourQty) {
		return hourlyPaymentClassificationWithMultipleTimeCard(hourlyWage, Arrays.asList(workingHourQty));
	}

	private HourlyPaymentClassification hourlyPaymentClassificationWithMultipleTimeCard(int hourlyWage, List<Integer> workingHourQtys) {
		HourlyPaymentClassification hourlyPaymentClassification = mock(HourlyPaymentClassification.class, CALLS_REAL_METHODS);
		when(hourlyPaymentClassification.getHourlyWage()).thenReturn(hourlyWage);
		
		ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>() {{
				for (Integer workingHourQty : workingHourQtys) {
					add(timeCardMock(workingHourQty));
				}
			}};
		when(hourlyPaymentClassification.getTimeCardsIn(ANY_ITERVAL)).thenReturn(timeCards);
		return hourlyPaymentClassification;
	}

	private TimeCard timeCardMock(int workingHourQty) {
		TimeCard timeCard = mock(TimeCard.class);
		when(timeCard.getWorkingHourQty()).thenReturn(workingHourQty);
		return timeCard;
	}

}
