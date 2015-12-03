package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentMethod;

public class JPAPaymentMethodFactory {
	public static JPAPaymentMethod create(PaymentMethod paymentMethod) {
		if(paymentMethod instanceof HoldPaymentMethod) {
			return JPAPaymentMethod.HOLD;
		}
		else
			throw new NotImplementedException();
	}
	
}
