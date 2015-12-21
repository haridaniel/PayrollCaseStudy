package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public abstract class NoAffiliation implements Affiliation {

	@Override
	public int calculateDeductionsAmount(DateInterval payInterval) {
		return 0;
	}

}
