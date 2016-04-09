package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentMethodTypeResponse;

public class PaymentMethodTypeResponseFormatter {

	public String format(PaymentMethodTypeResponse paymentTypeResponse) {
		switch (paymentTypeResponse) {
		case DIRECT:
			return "Direct";
		case HOLD:
			return "Hold";
		default:
			throw new RuntimeException("not implemented");
		}
	}

}
