package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;

public class SendPayRequest implements Request {

	public List<PayCheck> payChecks;

	public SendPayRequest(List<PayCheck> payChecks) {
		this.payChecks = payChecks;
	}
}