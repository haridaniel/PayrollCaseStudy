package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation;

import java.time.DayOfWeek;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public abstract class UnionMemberAffiliation implements Affiliation {

	public abstract int getUnionMemberId();
	public abstract int getWeeklyDueAmount();

	public abstract void addServiceCharge(ServiceCharge serviceCharge);
	public abstract Collection<ServiceCharge> getServiceChargesIn(DateInterval dateInterval);

	@Override
	public int calculateDeductionsAmount(DateInterval payInterval) {
		return calculateWeeklyDuesAmount(payInterval) + calculateServiceChargesAmount(payInterval);
	}

	private int calculateWeeklyDuesAmount(DateInterval payInterval) {
		return countFridaysInInterval(payInterval) * getWeeklyDueAmount();
	}

	private int calculateServiceChargesAmount(DateInterval dateInterval) {
		return getServiceChargesIn(dateInterval).stream()
				.mapToInt(serviceCharge -> serviceCharge.getAmount())
				.sum();
	}

	private int countFridaysInInterval(DateInterval dateInterval) {
		return dateInterval.countWeekDayInclusive(DayOfWeek.FRIDAY);
	}

}
