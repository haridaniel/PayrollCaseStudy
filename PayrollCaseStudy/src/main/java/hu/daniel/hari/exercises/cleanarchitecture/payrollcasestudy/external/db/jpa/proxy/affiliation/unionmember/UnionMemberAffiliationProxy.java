package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.JPAServiceChargeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.AffiliationProxy;

public class UnionMemberAffiliationProxy extends UnionMemberAffiliation implements AffiliationProxy {

	private JPAUnionMemberAffiliation jpaUnionMemberAffiliation;

	@Inject private JPAServiceChargeDao jpaServiceChargeDao;
	@Inject private ProxyFactory proxyFactory;
	
	@Inject 
	public UnionMemberAffiliationProxy(JPAUnionMemberAffiliation jpaUnionMemberAffiliation) {
		this.jpaUnionMemberAffiliation = jpaUnionMemberAffiliation;
	}

	@Override
	public int getUnionMemberId() {
		return jpaUnionMemberAffiliation.getUnionMemberId();
	}

	@Override
	public int getWeeklyDueAmount() {
		return jpaUnionMemberAffiliation.getWeeklyDueAmount();
	}

	@Override
	public void addServiceCharge(ServiceCharge serviceCharge) {
		jpaUnionMemberAffiliation.addServiceCharge(((ServiceChargeProxy) serviceCharge).getJpaServiceCharge());
	}

	@Override
	public Collection<ServiceCharge> getServiceChargesIn(DateInterval dateInterval) {
		return proxyAll(jpaServiceChargeDao.findJPAServiceChargesIn(dateInterval));
	}

	private List<ServiceCharge> proxyAll(Collection<JPAServiceCharge> jpaServiceCharges) {
		return jpaServiceCharges
				.stream()
				.map(jpaServiceCharge -> proxy(jpaServiceCharge))
				.collect(Collectors.toList());
	}

	private ServiceChargeProxy proxy(JPAServiceCharge jpaServiceCharge) {
		return proxyFactory.create(ServiceChargeProxy.class, jpaServiceCharge);
	}

	@Override
	public JPAAffiliation getJpaAffiliation() {
		return jpaUnionMemberAffiliation;
	}

}
