package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;

public class TimeCardImpl extends TimeCard {
	private LocalDate date;
	private int workingHourQty;

	public TimeCardImpl(LocalDate date, int workingHoursQty) {
		this.date = date;
		this.workingHourQty = workingHoursQty;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getWorkingHourQty() {
		return workingHourQty;
	}

	public void setWorkingHourQty(int workingHourQty) {
		this.workingHourQty = workingHourQty;
	}

	
	
}
