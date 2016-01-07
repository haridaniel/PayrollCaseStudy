package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod;

public interface PaymentMethod {

	public static interface PaymentMethodFactory {
		PaymentMethod holdPaymentMethod();
		PaymentMethod directPaymentMethod(String accountNumber);
	}

}
