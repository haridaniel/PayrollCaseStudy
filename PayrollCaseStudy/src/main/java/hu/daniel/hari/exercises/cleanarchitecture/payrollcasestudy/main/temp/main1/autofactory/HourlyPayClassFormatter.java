package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.Bind;

@Bind(HourlyPaymentType.class)
public class HourlyPayClassFormatter implements PayClassFormatter<HourlyPaymentType> {

	@Override
	public String format(HourlyPaymentType paymentType) {
		return "HOURLY FORMAT: " + paymentType.getHourlyWage();
	}
	
}
