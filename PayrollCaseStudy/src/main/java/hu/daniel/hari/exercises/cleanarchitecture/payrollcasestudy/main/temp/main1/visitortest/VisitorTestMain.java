package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.visitortest;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.HourlyPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalariedPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;

public class VisitorTestMain {
	
	public static void main(String[] args) {
		
		
		List<PaymentType> paymentTypes = new ArrayList<>();
		
		paymentTypes.add(new HourlyPaymentTypeImpl(11));
		paymentTypes.add(new SalariedPaymentTypeImpl(1002));

		
		PayClassFormatter payClassFormatter = new PayClassFormatter();
		
		for (PaymentType paymentType : paymentTypes) {
			String formattedPayClass = paymentType.accept(payClassFormatter);
			System.out.println(formattedPayClass);
		}
		
		
		
	}
	
}
