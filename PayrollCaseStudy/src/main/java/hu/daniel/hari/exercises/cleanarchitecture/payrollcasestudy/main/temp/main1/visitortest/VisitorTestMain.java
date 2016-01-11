package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.visitortest;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.HourlyPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalariedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;

public class VisitorTestMain {
	
	public static void main(String[] args) {
		
		
		List<PaymentClassification> paymentClassifications = new ArrayList<>();
		
		paymentClassifications.add(new HourlyPaymentClassificationImpl(11));
		paymentClassifications.add(new SalariedPaymentClassificationImpl(1002));

		
		PayClassFormatter payClassFormatter = new PayClassFormatter();
		
		for (PaymentClassification paymentClassification : paymentClassifications) {
			String formattedPayClass = paymentClassification.accept(payClassFormatter);
			System.out.println(formattedPayClass);
		}
		
		
		
	}
	
}
