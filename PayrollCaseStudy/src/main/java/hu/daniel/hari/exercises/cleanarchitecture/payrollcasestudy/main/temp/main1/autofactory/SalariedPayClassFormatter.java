package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.Bind;

@Bind(SalariedPaymentType.class)
public class SalariedPayClassFormatter implements PayClassFormatter<SalariedPaymentType> {

	@Override
	public String format(SalariedPaymentType paymentType) {
		return "SALARIED FORMAT: " + paymentType.getMonthlySalary();
	}
	
}
