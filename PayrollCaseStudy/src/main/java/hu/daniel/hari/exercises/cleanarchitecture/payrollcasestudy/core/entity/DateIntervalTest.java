package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;

public class DateIntervalTest {
	private static final LocalDate INTERVAL_FROM = LocalDate.of(2015, 12, 01);
	private static final LocalDate INTERVAL_TO = LocalDate.of(2015, 12, 10);
	private static final LocalDate A_DATE_BETWEEN_FROM_AND_TO = LocalDate.of(2015, 12, 3);
	private static final LocalDate A_DATE_BEFORE_FROM = LocalDate.of(2015, 11, 30);
	private static final LocalDate A_DATE_AFTER_TO = LocalDate.of(2015, 12, 11);
	
	private DateInterval dateInterval = DateInterval.of(INTERVAL_FROM, INTERVAL_TO);
	private DateInterval thisIntervalHas4Fridays = DateInterval.of(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 31));

	@Test
	public void testIsBetweenInclusive_true() {
		assertThat(dateInterval.isBetweenInclusive(INTERVAL_FROM), is(true));
		assertThat(dateInterval.isBetweenInclusive(INTERVAL_TO), is(true));
		assertThat(dateInterval.isBetweenInclusive(A_DATE_BETWEEN_FROM_AND_TO), is(true));
	}
	
	@Test
	public void testIsBetweenInclusive_false() {
		assertThat(dateInterval.isBetweenInclusive(A_DATE_BEFORE_FROM), is(false));
		assertThat(dateInterval.isBetweenInclusive(A_DATE_AFTER_TO), is(false));
	}
	
	//TODO: Not tested boundaries 
	@Test
	public void testCountWeekDayInclusive() {
		assertThat(thisIntervalHas4Fridays.countWeekDayInclusive(DayOfWeek.FRIDAY), is(4));
	}

}