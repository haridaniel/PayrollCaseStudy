package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels;

import java.time.LocalDate;

public class AddServiceChargeRequestModel {
	public final int unionMemberId;
	public final LocalDate date;
	public final int amount;

	public AddServiceChargeRequestModel(int unionMemberId, LocalDate date, int amount) {
		this.unionMemberId = unionMemberId;
		this.date = date;
		this.amount = amount;
	}
}
