package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentmethod.JPAHoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.HoldPaymentMethod;

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
