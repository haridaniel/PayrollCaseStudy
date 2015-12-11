package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public class NoAffiliation implements Affiliation {

	@Override
	public int calculateDeductionsAmount(DateInterval payInterval) {
		return 0;
	}

}
