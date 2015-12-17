package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;

public class UnionMemberAffiliationImpl extends UnionMemberAffiliation {

	private int unionMemberId;
	private int weeklyDueAmount;

	private Map<LocalDate, ServiceCharge> serviceChargesByDate = new HashMap<>();
	
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

	@Override
	public void addServiceCharge(ServiceCharge serviceCharge) {
		serviceChargesByDate.put(serviceCharge.getDate(), serviceCharge);
	}

	@Override
	public Collection<ServiceCharge> getServiceChargesIn(DateInterval dateInterval) {
		return serviceChargesByDate.keySet().stream()
				.filter(date -> dateInterval.isBetweenInclusive(date))
				.map(date -> serviceChargesByDate.get(date))
				.collect(Collectors.toList());
	}

}
