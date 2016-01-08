package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import java.util.List;

public class GeneratePayResponse implements Response {
	public List<PayCheckResponse> payCheckResponses;

	public GeneratePayResponse(List<PayCheckResponse> payCheckResponses) {
		this.payCheckResponses = payCheckResponses;
	}
	
	public static class PayCheckResponse {
		public int employeeId;
		public String name;
		public int grossAmount;
		public int deductionsAmount;
		public int netAmount;
	}

}
