package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;

public class PayClassFormatterFactory extends AnnotationBindedClassSelectorFactory<PayClassFormatter, PaymentType>{

	public PayClassFormatterFactory() {
		super(PayClassFormatter.class, PaymentType.class);
	}
	

	@Override
	public <CS extends PaymentType> PayClassFormatter create(Class<CS> selectorClass) {
		return super.create(selectorClass);
	}
}
