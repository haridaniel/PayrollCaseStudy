package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory;

import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;

public interface ClassSelectorFactory<T, S> {
	
	<CS extends S> T create(Class<CS> selectorClass);
	
}
