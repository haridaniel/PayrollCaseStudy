package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.PayClassFormatter;

public class ClassBindMain {

	
	public static void main(String[] args) {
		
		
		 
		ClassBindingsProvider<PayClassFormatter, PaymentClassification> classBindingsProvider = 
				ClassPathScannerClassBindingsProvider.of(PayClassFormatter.class, PaymentClassification.class, Constants.APP_ROOT_PACKAGE);
		
		System.out.println(classBindingsProvider.getClassBindings());
		
		
	}
	
	
}
