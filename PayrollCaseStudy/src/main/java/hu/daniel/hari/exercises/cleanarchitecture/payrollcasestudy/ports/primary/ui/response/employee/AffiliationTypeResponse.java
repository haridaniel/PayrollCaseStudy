package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.employee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;

public enum AffiliationTypeResponse {
	NO,
	UNION_MEMBER
	;

	//TODO: maybe factories go away from here to main package
	public static class AffiliationTypeResponseFactory {
		public AffiliationTypeResponse create(Affiliation affiliation) {
			if(affiliation instanceof NoAffiliation)
				return AffiliationTypeResponse.NO;
			if(affiliation instanceof UnionMemberAffiliation)
				return AffiliationTypeResponse.UNION_MEMBER;
			throw new RuntimeException("not implemented");
		}
		
	}

}

