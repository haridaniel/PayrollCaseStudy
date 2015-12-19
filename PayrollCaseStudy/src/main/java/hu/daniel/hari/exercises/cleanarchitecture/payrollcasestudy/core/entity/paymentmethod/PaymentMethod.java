package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod;

public interface PaymentMethod {

	public static interface PaymentMethodFactory {
		PaymentMethod holdPaymentMethod();
	}

}
