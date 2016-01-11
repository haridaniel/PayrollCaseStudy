package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;

public abstract class AbstractPayClassFormatter<T extends PaymentClassification> {
	public String format(PaymentClassification paymentClassification) {
		return doFormat((T) paymentClassification);
	}

	protected abstract String doFormat(T paymentClassification);

}
