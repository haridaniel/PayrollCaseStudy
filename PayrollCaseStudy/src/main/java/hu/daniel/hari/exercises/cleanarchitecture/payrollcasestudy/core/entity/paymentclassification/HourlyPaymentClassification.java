package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

public class HourlyPaymentClassification extends PaymentClassification{

	public int hourlyRate;

	public HourlyPaymentClassification(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
}
