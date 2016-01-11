package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;


import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.DefaultClassSelectorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.AnnotationClassBindingsConfig;

public class PayClassFormatterFactoryOLD extends DefaultClassSelectorFactory<PayClassFormatter, PaymentClassification> {

	public PayClassFormatterFactoryOLD() {
		super(AnnotationClassBindingsConfig.of(PayClassFormatter.class, PaymentClassification.class));
	}

}
