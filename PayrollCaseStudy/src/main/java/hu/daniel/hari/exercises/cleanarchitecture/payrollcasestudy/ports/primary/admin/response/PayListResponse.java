package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.PaymentMethodTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.paymenttype.PaymentTypeResponse;

public class PayListResponse implements Response {
	public List<PayListResponseItem> payListResponseItems;

	public PayListResponse(List<PayListResponseItem> payListResponseItems) {
		this.payListResponseItems = payListResponseItems;
	}
	
	public static class PayListResponseItem {
		public int employeeId;
		public int grossAmount;
		public int deductionsAmount;
		public int netAmount;
		public String name;
		public PaymentTypeResponse paymentTypeResponse;
		public PaymentMethodTypeResponse paymentMethodTypeResponse;
	}

}
