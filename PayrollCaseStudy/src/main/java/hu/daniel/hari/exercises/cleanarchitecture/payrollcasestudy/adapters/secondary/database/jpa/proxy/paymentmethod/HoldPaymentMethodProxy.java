package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod.JPAHoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.HoldPaymentMethod;

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
