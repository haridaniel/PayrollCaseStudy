package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentmethod.JPAPaymentMethod;

public interface PaymentMethodProxy extends PaymentMethod {

	public abstract JPAPaymentMethod getJPAPaymentMethod();
	
}
