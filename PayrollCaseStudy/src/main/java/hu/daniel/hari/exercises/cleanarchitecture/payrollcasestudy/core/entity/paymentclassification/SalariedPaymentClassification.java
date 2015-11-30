package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

public class SalariedPaymentClassification extends PaymentClassification {
	
	public int monthlySalary;

	public SalariedPaymentClassification(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}
