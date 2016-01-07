package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request;

import java.time.LocalDate;

public class GeneratePayRequest implements Request {
	public LocalDate payDate;

	public GeneratePayRequest(LocalDate payDate) {
		this.payDate = payDate;
	}
}