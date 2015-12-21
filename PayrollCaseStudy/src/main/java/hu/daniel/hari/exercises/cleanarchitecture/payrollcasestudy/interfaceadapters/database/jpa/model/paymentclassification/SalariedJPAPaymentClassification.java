package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification;

import javax.persistence.Entity;

@Entity
public class SalariedJPAPaymentClassification extends JPAPaymentClassification {
	
	private int monthlySalary;

	public SalariedJPAPaymentClassification() {}
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
