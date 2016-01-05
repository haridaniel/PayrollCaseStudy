package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.fulfiller;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;

public interface PaymentFulFillerFactory {
	PaymentFulFiller createFor(Class<? extends PaymentMethod> paymentMethodType);
}
