package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.HoldPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.PaymentMethodProxy;

public class PaymentMethodProxyFactory {

	public static PaymentMethodProxy create(JPAPaymentMethod jpaPaymentMethod) {
		switch (jpaPaymentMethod) {
		case HOLD:
			return new HoldPaymentMethodProxy();
		default:
			throw new NotImplementedException();
		}
	}

}
