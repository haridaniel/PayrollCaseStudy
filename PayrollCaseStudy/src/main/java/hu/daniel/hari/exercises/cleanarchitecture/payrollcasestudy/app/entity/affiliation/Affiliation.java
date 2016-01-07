package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public interface Affiliation {

	public abstract int calculateDeductionsAmount(DateInterval payInterval);

	public static interface AffiliationFactory {
		NoAffiliation noAffiliation();
		UnionMemberAffiliation unionMemberAffiliation(int unionMemberId, int weeklyDueAmount);
	}
	
}
