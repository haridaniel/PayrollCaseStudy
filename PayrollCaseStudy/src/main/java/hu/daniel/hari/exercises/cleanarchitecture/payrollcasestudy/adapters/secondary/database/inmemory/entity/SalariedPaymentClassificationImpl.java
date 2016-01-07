package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;

public class SalariedPaymentClassificationImpl extends SalariedPaymentClassification {

	private int monthlySalary;

	public SalariedPaymentClassificationImpl(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public int getMonthlySalary() {
		return monthlySalary;
	}

	@Override
	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

}
