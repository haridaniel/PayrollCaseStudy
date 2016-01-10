package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.HourlyPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalariedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;

public class AutoFactoryMain {

	public static void main(String[] args) {
		
		List<PaymentClassification> paymentClassifications = new ArrayList<>();
		
		paymentClassifications.add(new HourlyPaymentClassificationImpl(10));
		paymentClassifications.add(new SalariedPaymentClassificationImpl(1000));
		

		PayClassFormatterFactory payClassFormatterFactory = new PayClassFormatterFactory();
		
		for (PaymentClassification paymentClassification : paymentClassifications) {
			String format = payClassFormatterFactory.create(paymentClassification.getClass()).format(paymentClassification);
			System.out.println(format);
		}
		
				
	}
	
}
