package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller;

public interface PaymentFulFillerFactory {
	
	PaymentFulFiller directPaymentFulFiller();
	PaymentFulFiller holdPaymentFulFiller();
	PaymentFulFiller mailPaymentFulFiller();

}
