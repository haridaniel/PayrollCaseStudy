package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.BindSource;

@BindSource(HourlyPaymentClassification.class)
public class HourlyPayClassFormatter implements PayClassFormatter<HourlyPaymentClassification> {

	@Override
	public String format(HourlyPaymentClassification paymentClassification) {
		return "HOURLY FORMAT";
	}
	
}
