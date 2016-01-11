package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;

public class PayClassFormatterFactory extends AnnotationBindedClassSelectorFactory<PayClassFormatter, PaymentClassification>{

	public PayClassFormatterFactory() {
		super(PayClassFormatter.class, PaymentClassification.class);
	}
	

	@Override
	public <CS extends PaymentClassification> PayClassFormatter create(Class<CS> selectorClass) {
		return super.create(selectorClass);
	}
}
