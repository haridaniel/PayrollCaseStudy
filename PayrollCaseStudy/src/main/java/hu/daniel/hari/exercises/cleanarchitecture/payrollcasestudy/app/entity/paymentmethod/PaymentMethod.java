package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod;

public interface PaymentMethod {

	public abstract <T> T accept(PaymentMethodVisitor<T> visitor);
	
	public static interface PaymentMethodFactory {
		PaymentMethod holdPaymentMethod();
		PaymentMethod directPaymentMethod(String accountNumber);
	}

	public interface PaymentMethodVisitor<T> {
		public T visit(HoldPaymentMethod holdPaymentMethod);
		public T visit(DirectPaymentMethod directPaymentMethod);
	}

	
}
