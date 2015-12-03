package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentMethod;

public class PaymentMethodProxyFactory {

	public static PaymentMethod create(JPAPaymentMethod jpaPaymentMethod) {
		switch (jpaPaymentMethod) {
		case HOLD:
			return new HoldPaymentMethod();
		default:
			throw new NotImplementedException();
		}
	}

}
