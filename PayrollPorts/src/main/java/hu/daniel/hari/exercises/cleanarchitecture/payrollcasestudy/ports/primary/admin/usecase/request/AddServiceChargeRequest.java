package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request;

import java.time.LocalDate;

public class AddServiceChargeRequest implements Request {
	public final int unionMemberId;
	public final LocalDate date;
	public final int amount;

	public AddServiceChargeRequest(int unionMemberId, LocalDate date, int amount) {
		this.unionMemberId = unionMemberId;
		this.date = date;
		this.amount = amount;
	}
}
