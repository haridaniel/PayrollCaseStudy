package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.common.ThrowMap;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.PaymentMethodTypeResponse;

public class PaymentMethodTypeResponseToStringFormatter extends ThrowMap<PaymentMethodTypeResponse, String> {
	
	public PaymentMethodTypeResponseToStringFormatter() {
		put(PaymentMethodTypeResponse.DIRECT, "Direct");
		put(PaymentMethodTypeResponse.PAYMASTER, "Paymaster hold");
	}
	
	public String format(PaymentMethodTypeResponse response) {
		return getOrThrow(response);
	}
	
}

