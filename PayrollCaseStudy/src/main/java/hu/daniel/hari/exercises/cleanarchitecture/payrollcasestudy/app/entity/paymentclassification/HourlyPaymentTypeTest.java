package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public class HourlyPaymentTypeTest {

	private static final DateInterval ANY_ITERVAL = null;

	@Test
	public void calculatePayForOneTimeCard_ForNormalHours() throws Exception {
		HourlyPaymentType hourlyPaymentType = hourlyPaymentTypeWithOneTimeCard(10, 8);
		assertThat(hourlyPaymentType.calculateAmount(ANY_ITERVAL), is(80));
	}

	@Test
	public void calculatePayForMultipleTimeCard_ForNormalHours() throws Exception {
		HourlyPaymentType hourlyPaymentType = hourlyPaymentTypeWithMultipleTimeCard(10, Arrays.asList(8, 5, 7));
		assertThat(hourlyPaymentType.calculateAmount(ANY_ITERVAL), is((8 + 5 + 7) * 10));
	}

	@Test
	public void calculatePayForOneTimeCard_ForOvertimeHours() throws Exception {
		HourlyPaymentType hourlyPaymentType = hourlyPaymentTypeWithOneTimeCard(10, 10);
		int calculateAmount = hourlyPaymentType.calculateAmount(ANY_ITERVAL);
		int standard = 10 * 8;
		int overtime = (int) (10d * 1.5 * 2);
		assertThat(calculateAmount, is(standard + overtime));
	}

	private HourlyPaymentType hourlyPaymentTypeWithOneTimeCard(int hourlyWage, int workingHourQty) {
		return hourlyPaymentTypeWithMultipleTimeCard(hourlyWage, Arrays.asList(workingHourQty));
	}

	private HourlyPaymentType hourlyPaymentTypeWithMultipleTimeCard(int hourlyWage, List<Integer> workingHourQtys) {
		HourlyPaymentType hourlyPaymentType = mock(HourlyPaymentType.class, CALLS_REAL_METHODS);
		when(hourlyPaymentType.getHourlyWage()).thenReturn(hourlyWage);
		
		ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>() {{
				for (Integer workingHourQty : workingHourQtys) {
					add(timeCardMock(workingHourQty));
				}
			}};
		when(hourlyPaymentType.getTimeCardsIn(ANY_ITERVAL)).thenReturn(timeCards);
		return hourlyPaymentType;
	}

	private TimeCard timeCardMock(int workingHourQty) {
		TimeCard timeCard = mock(TimeCard.class);
		when(timeCard.getWorkingHourQty()).thenReturn(workingHourQty);
		return timeCard;
	}

}
