package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.BindSource;

@BindSource(SalariedPaymentClassification.class)
public class SalariedPayClassFormatter implements PayClassFormatter<SalariedPaymentClassification> {

	@Override
	public String format(SalariedPaymentClassification paymentClassification) {
		return "SALARIED FORMAT";
	}
	
}
