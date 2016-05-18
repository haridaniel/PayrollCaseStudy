package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity;

import java.time.LocalDate;

/**
 * Business rule constants
 */
public class Constants {

	/** when starts the bi-week (odd or even week) **/
	public static final LocalDate BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY = LocalDate.of(2015, 12, 11);
	
	public static final int HOURLY_PAYMENTTYPE_DAILY_NORMAL_HOURS = 8;
	public static final double HOURLY_PAYMENTTYPE_OVERTIME_WAGE_MULTIPLIER = 1.5d;

}
