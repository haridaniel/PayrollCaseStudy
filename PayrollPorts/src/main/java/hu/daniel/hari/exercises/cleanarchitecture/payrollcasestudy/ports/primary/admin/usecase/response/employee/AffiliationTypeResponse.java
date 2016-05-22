package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;

public enum AffiliationTypeResponse {
	NO,
	UNION_MEMBER
	;

	public interface AffiliationTypeResponseFactory {
		AffiliationTypeResponse create(Affiliation affiliation);
	}

}

