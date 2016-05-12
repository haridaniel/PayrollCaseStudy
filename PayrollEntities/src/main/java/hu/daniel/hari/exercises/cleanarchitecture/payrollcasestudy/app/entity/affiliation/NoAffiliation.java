package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class NoAffiliation implements Affiliation {

	@Override
	public int calculateDeductionsAmount(DateInterval payInterval) {
		return 0;
	}

}
