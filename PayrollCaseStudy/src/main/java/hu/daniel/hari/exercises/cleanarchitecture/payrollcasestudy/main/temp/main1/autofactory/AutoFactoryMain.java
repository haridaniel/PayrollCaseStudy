package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Stopwatch;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.HourlyPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalariedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;

public class AutoFactoryMain {

	public static void main(String[] args) {
		
		List<PaymentClassification> paymentClassifications = new ArrayList<>();
		
		paymentClassifications.add(new HourlyPaymentClassificationImpl(10));
		paymentClassifications.add(new SalariedPaymentClassificationImpl(1000));
		
		Stopwatch stopwatch = new Stopwatch().start();
		PayClassFormatterFactory payClassFormatterFactory = new PayClassFormatterFactory();
		System.out.println(stopwatch.toString());
		
		for (PaymentClassification paymentClassification : paymentClassifications) {
			String format = payClassFormatterFactory.create(paymentClassification.getClass()).format(paymentClassification);
			System.out.println(format);
		}
		
		
		PayClassFormatter formatter = payClassFormatterFactory.create(HourlyPaymentClassification.class);
		String format2 = formatter.format(new HourlyPaymentClassificationImpl(2));
				
	}
	
}
