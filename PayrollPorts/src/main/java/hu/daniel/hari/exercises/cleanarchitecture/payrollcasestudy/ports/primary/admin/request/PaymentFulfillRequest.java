package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request;

import java.time.LocalDate;

public class PaymentFulfillRequest implements Request {
	public LocalDate payDate;

	public PaymentFulfillRequest(LocalDate payDate) {
		this.payDate = payDate;
	}

}