package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.Request;

public class RemoveUnionMemberAffiliationRequest implements Request {
	public int unionMemberId;

	public RemoveUnionMemberAffiliationRequest(int unionMemberId) {
		this.unionMemberId = unionMemberId;
	}
}