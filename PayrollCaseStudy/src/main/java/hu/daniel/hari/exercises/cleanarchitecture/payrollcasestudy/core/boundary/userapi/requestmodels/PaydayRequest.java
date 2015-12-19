package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels;

import java.time.LocalDate;

public class PaydayRequest implements Request {
	public LocalDate payDate;

	public PaydayRequest(LocalDate payDate) {
		this.payDate = payDate;
	}
}