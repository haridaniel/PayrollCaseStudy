package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Stopwatch;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.HourlyPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalariedPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;

public class AutoFactoryMain {

	public static void main(String[] args) {
		
		List<PaymentType> paymentTypes = new ArrayList<>();
		
		paymentTypes.add(new HourlyPaymentTypeImpl(10));
		paymentTypes.add(new SalariedPaymentTypeImpl(1000));
		
		Stopwatch stopwatch = Stopwatch.createStarted();
		PayClassFormatterFactory payClassFormatterFactory = new PayClassFormatterFactory();
		System.out.println(stopwatch.toString());
		
		for (PaymentType paymentType : paymentTypes) {
			String format = payClassFormatterFactory.create(paymentType.getClass()).format(paymentType);
			System.out.println(format);
		}
		
		
		PayClassFormatter formatter = payClassFormatterFactory.create(HourlyPaymentType.class);
		String format2 = formatter.format(new HourlyPaymentTypeImpl(2));
				
	}
	
}
