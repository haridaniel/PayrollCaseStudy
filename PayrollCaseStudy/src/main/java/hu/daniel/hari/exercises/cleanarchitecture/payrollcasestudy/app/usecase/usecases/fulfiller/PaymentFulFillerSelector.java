package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.direct.DirectPaymentFulFiller;

public class PaymentFulFillerSelector {
	
	private DirectPaymentFulFiller directPaymentFulFiller;

	public PaymentFulFillerSelector(DirectPaymentFulFiller directPaymentFulFiller) {
		this.directPaymentFulFiller = directPaymentFulFiller;
	}

	public PaymentFulFiller forPaymentMethodType(Class<? extends PaymentMethod> paymentMethodType) {
		if (paymentMethodType.isAssignableFrom(HoldPaymentMethod.class))
			return null;
		else if (paymentMethodType.isAssignableFrom(DirectPaymentMethod.class))
			return directPaymentFulFiller;
		else
			throw new IllegalArgumentException();
	}
	
}
