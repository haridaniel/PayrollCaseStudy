package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.employee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymasterPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodVisitor;

public enum PaymentMethodTypeResponse {
	DIRECT,
	PAYMASTER
	;
	
	public static class PaymentMethodTypeResponseFactory implements PaymentMethodVisitor<PaymentMethodTypeResponse>{
		@Override
		public PaymentMethodTypeResponse visit(PaymasterPaymentMethod paymasterPaymentMethod) {
			return PaymentMethodTypeResponse.PAYMASTER;
		}

		@Override
		public PaymentMethodTypeResponse visit(DirectPaymentMethod directPaymentMethod) {
			return PaymentMethodTypeResponse.DIRECT;
		}
		
	}

}

