package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPANoAffiliation;

public class NoAffiliationProxy extends NoAffiliation implements AffiliationProxy {

	private JPANoAffiliation jpaNoAffiliation;

	public NoAffiliationProxy(JPANoAffiliation jpaNoAffiliation) {
		this.jpaNoAffiliation = jpaNoAffiliation;
	}
	
	@Override
	public JPAAffiliation getJpaAffiliation() {
		return jpaNoAffiliation;
	}

}
