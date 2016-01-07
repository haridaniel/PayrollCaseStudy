package hu.daniel.hari.testthis.data.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.testthis.data.jpa.model.paymentmethod.JPAHoldPaymentMethod;
import hu.daniel.hari.testthis.data.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.testthis.data.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPAHoldPaymentMethod.class)
public class HoldPaymentMethodProxy extends HoldPaymentMethod implements PaymentMethodProxy {

	private JPAPaymentMethod jpaPaymentMethod;

	public HoldPaymentMethodProxy(JPAHoldPaymentMethod jpaPaymentMethod) {
		this.jpaPaymentMethod = jpaPaymentMethod;
	}

	@Override
	public JPAPaymentMethod getJPAObject() {
		return jpaPaymentMethod;
	}

}
