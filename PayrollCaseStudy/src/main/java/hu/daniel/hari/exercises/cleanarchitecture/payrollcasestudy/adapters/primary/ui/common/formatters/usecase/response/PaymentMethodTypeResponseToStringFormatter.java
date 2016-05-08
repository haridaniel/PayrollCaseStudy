package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.usecase.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.PaymentMethodTypeResponse;

public class PaymentMethodTypeResponseToStringFormatter {

	public String format(PaymentMethodTypeResponse paymentTypeResponse) {
		switch (paymentTypeResponse) {
		case DIRECT:
			return "Direct";
		case PAYMASTER:
			return "Paymaster hold";
		default:
			throw new RuntimeException("not implemented");
		}
	}

}
