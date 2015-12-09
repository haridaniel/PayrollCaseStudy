package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification;

import javax.persistence.Entity;

@Entity
public class SalariedJPAPaymentClassification extends JPAPaymentClassification {
	
	private int monthlySalary;

	public SalariedJPAPaymentClassification(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}
