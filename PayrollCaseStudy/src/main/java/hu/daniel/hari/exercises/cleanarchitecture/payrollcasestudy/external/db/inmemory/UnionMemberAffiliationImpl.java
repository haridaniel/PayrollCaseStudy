package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;

public class UnionMemberAffiliationImpl extends UnionMemberAffiliation {

	private int unionMemberId;
	private int weeklyDueAmount;

	public UnionMemberAffiliationImpl(int unionMemberId, int weeklyDueAmount) {
		this.unionMemberId = unionMemberId;
		this.weeklyDueAmount = weeklyDueAmount;
	}

	@Override
	public int getUnionMemberId() {
		return unionMemberId;
	}
	
	@Override
	public int getWeeklyDueAmount() {
		return weeklyDueAmount;
	}

}
