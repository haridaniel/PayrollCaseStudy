package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;


import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.DefaultAutoFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.ClassPathScannerClassBindingsProvider;

public class PayClassFormatterFactory extends DefaultAutoFactory<PayClassFormatter, PaymentClassification> {

	public PayClassFormatterFactory() {
		super(ClassPathScannerClassBindingsProvider.of(PayClassFormatter.class, PaymentClassification.class, Constants.APP_ROOT_PACKAGE));
	}

}
