package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentMethod;

public class HoldPaymentMethodProxy extends HoldPaymentMethod implements PaymentMethodProxy {

	@Override
	public JPAPaymentMethod getJPAPaymentMethod() {
		return JPAPaymentMethod.HOLD;
	}

	
	
}
