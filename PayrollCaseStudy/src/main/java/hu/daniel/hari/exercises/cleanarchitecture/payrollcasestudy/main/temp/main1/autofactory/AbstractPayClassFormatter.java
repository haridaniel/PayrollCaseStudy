package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;

public abstract class AbstractPayClassFormatter<T extends PaymentType> {
	public String format(PaymentType paymentType) {
		return doFormat((T) paymentType);
	}

	protected abstract String doFormat(T paymentType);

}
