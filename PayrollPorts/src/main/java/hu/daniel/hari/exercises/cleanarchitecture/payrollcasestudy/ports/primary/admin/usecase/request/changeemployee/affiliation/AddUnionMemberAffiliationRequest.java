package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.ChangeEmployeeRequest;

public class AddUnionMemberAffiliationRequest extends ChangeEmployeeRequest {
	public int unionMemberId;
	public int weeklyDueAmount;

	public AddUnionMemberAffiliationRequest(int employeeId, int unionMemberId, int weeklyDueAmount) {
		super(employeeId);
		this.unionMemberId = unionMemberId;
		this.weeklyDueAmount = weeklyDueAmount;
	}
}