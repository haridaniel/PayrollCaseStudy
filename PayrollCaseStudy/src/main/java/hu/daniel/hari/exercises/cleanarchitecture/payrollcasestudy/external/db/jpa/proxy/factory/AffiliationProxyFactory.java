package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.factory;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPANoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.AffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.NoAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.UnionMemberAffiliationProxy.UnionMemberAffiliationProxyFactory;

public class AffiliationProxyFactory {

	@Inject private UnionMemberAffiliationProxyFactory unionMemberAffiliationProxyFactory;
	
	public AffiliationProxy create(JPAAffiliation jpaAffiliation) {
		if (jpaAffiliation instanceof JPANoAffiliation)
			return new NoAffiliationProxy((JPANoAffiliation) jpaAffiliation);
		else if (jpaAffiliation instanceof JPAUnionMemberAffiliation)
			return unionMemberAffiliationProxyFactory.create((JPAUnionMemberAffiliation) jpaAffiliation);
		else
			throw new NotImplementedException();
	}

}
