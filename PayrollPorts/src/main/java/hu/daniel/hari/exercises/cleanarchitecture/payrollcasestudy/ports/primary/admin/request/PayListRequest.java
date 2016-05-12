package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request;

import java.time.LocalDate;

public class PayListRequest implements Request {
	public LocalDate payDate;

	public PayListRequest(LocalDate payDate) {
		this.payDate = payDate;
	}
}