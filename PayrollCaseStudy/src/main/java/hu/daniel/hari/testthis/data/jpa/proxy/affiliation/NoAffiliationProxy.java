package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation.JPANoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.NoAffiliation;

@AutoBindedProxy(JPANoAffiliation.class)
public class NoAffiliationProxy extends NoAffiliation implements AffiliationProxy {

	private JPANoAffiliation jpaNoAffiliation;

	public NoAffiliationProxy(JPANoAffiliation jpaNoAffiliation) {
		this.jpaNoAffiliation = jpaNoAffiliation;
	}
	
	@Override
	public JPAAffiliation getJPAObject() {
		return jpaNoAffiliation;
	}

}
