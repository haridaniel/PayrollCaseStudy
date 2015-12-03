package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize;

import javax.persistence.Entity;

@Entity
public class HourlyJPAPaymentSize extends JPAPaymentSize {

	private int hourlyWage;

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
	
}
