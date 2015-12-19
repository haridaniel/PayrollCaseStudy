package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeRequest;

public class AddUnionMemberAffiliationRequest extends ChangeEmployeeRequest {
	public int unionMemberId;
	public int weeklyDueAmount;

	public AddUnionMemberAffiliationRequest(int employeeId, int unionMemberId, int weeklyDueAmount) {
		super(employeeId);
		this.unionMemberId = unionMemberId;
		this.weeklyDueAmount = weeklyDueAmount;
	}
}