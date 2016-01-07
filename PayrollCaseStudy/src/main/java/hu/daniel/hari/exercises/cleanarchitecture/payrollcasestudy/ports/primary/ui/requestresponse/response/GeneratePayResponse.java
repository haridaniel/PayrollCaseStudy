package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;

public class GeneratePayResponse implements Response {
	public List<PayCheck> payChecks;

	public GeneratePayResponse(List<PayCheck> payChecks) {
		this.payChecks = payChecks;
	}

}
