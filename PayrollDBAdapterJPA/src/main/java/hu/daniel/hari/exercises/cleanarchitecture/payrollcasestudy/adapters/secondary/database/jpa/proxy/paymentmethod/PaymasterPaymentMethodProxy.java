package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod.JPAPaymasterPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.factory.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymasterPaymentMethod;

@AutoBindedProxy(JPAPaymasterPaymentMethod.class)
public class PaymasterPaymentMethodProxy extends PaymasterPaymentMethod implements PaymentMethodProxy {

	private JPAPaymentMethod jpaPaymentMethod;

	public PaymasterPaymentMethodProxy(JPAPaymasterPaymentMethod jpaPaymentMethod) {
		this.jpaPaymentMethod = jpaPaymentMethod;
	}

	@Override
	public JPAPaymentMethod getJPAObject() {
		return jpaPaymentMethod;
	}

}
