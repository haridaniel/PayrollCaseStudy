package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation;

import java.time.DayOfWeek;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public abstract class UnionMemberAffiliation implements Affiliation {

	public abstract int getUnionMemberId();
	public abstract int getWeeklyDueAmount();

	@Override
	public int calculateDeductionsAmount(DateInterval payInterval) {
		return countFridaysInInterval(payInterval) * getWeeklyDueAmount();
	}

	private int countFridaysInInterval(DateInterval dateInterval) {
		return dateInterval.countWeekDayInclusive(DayOfWeek.FRIDAY);
	}

}
