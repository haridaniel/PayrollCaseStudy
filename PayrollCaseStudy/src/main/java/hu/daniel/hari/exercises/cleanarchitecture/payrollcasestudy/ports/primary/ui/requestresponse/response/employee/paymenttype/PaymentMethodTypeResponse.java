package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodVisitor;

public enum PaymentMethodTypeResponse {
	DIRECT,
	HOLD
	;
	
	public static class PaymentMethodVisitorTypeResponseFactory implements PaymentMethodVisitor<PaymentMethodTypeResponse>{
		@Override
		public PaymentMethodTypeResponse visit(HoldPaymentMethod holdPaymentMethod) {
			return PaymentMethodTypeResponse.HOLD;
		}

		@Override
		public PaymentMethodTypeResponse visit(DirectPaymentMethod directPaymentMethod) {
			return PaymentMethodTypeResponse.DIRECT;
		}
		
	}

}

