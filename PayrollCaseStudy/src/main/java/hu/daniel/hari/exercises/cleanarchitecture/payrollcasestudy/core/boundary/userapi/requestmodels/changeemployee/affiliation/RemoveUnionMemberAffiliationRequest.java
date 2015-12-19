package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request;

public class RemoveUnionMemberAffiliationRequest implements Request {
	public int unionMemberId;

	public RemoveUnionMemberAffiliationRequest(int unionMemberId) {
		this.unionMemberId = unionMemberId;
	}
}