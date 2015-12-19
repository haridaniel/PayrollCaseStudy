package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.LocalDate;

public abstract class TimeCard {

	public abstract LocalDate getDate();
	public abstract int getWorkingHourQty();

	public abstract void setDate(LocalDate date); 
	public abstract void setWorkingHourQty(int workingHourQty); 
	
	public static interface TimeCardFactory {
		TimeCard timeCard(LocalDate date, int workingHoursQty);
	}
	
}
