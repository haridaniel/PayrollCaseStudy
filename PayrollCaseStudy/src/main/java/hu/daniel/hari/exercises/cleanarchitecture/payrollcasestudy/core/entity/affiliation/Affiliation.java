package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public interface Affiliation {

	public abstract int calculateDeductionsAmount(DateInterval payInterval);

}
