package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.LocalDate;

public class SalariedPaymentClassification extends PaymentClassification {
	
	private int monthlySalary;

	public SalariedPaymentClassification(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public int calculatePay(LocalDate date) {
		return monthlySalary;
	}
	
}
