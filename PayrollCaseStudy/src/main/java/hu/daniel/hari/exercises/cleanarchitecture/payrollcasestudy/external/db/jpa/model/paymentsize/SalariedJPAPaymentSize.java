package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize;

import javax.persistence.Entity;

@Entity
public class SalariedJPAPaymentSize extends JPAPaymentSize {
	
	private int monthlySalary;

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}
