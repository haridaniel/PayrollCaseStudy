package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse.AffiliationTypeResponseFactory;

public class AffiliationTypeResponseFactoryImpl implements AffiliationTypeResponseFactory {
	@Override
	public AffiliationTypeResponse create(Affiliation affiliation) {
		if(affiliation instanceof NoAffiliation)
			return AffiliationTypeResponse.NO;
		if(affiliation instanceof UnionMemberAffiliation)
			return AffiliationTypeResponse.UNION_MEMBER;
		throw new RuntimeException("not implemented");
	}
	
}