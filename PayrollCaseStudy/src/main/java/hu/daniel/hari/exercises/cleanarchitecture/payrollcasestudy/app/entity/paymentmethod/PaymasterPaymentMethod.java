package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod;

public abstract class PaymasterPaymentMethod implements PaymentMethod {
	
	@Override
	public <T> T accept(PaymentMethodVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
