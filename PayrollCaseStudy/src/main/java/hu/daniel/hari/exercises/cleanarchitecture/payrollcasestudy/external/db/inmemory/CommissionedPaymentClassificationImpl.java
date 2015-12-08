package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;

public class CommissionedPaymentClassificationImpl extends CommissionedPaymentClassification {

	private int biWeeklyBaseSalary;
	private double commissionRate;

	public CommissionedPaymentClassificationImpl(int biWeeklyBaseSalary, double commissionRate) {
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}

	@Override
	public int getBiWeeklyBaseSalary() {
		return biWeeklyBaseSalary;
	}

	@Override
	public double getCommissionRate() {
		return commissionRate;
	}

}
