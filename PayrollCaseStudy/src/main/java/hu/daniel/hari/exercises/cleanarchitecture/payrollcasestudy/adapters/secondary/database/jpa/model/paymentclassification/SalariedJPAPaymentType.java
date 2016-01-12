package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification;

import javax.persistence.Entity;

@Entity
public class SalariedJPAPaymentType extends JPAPaymentType {
	
	private int monthlySalary;

	public SalariedJPAPaymentType() {}
	public SalariedJPAPaymentType(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}
