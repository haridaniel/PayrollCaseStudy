package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.inmemory.entity;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;

public class ServiceChargeImpl implements ServiceCharge {

	private LocalDate date;
	private int amount;
	
	public ServiceChargeImpl(LocalDate date, int amount) {
		this.date = date;
		this.amount = amount;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
