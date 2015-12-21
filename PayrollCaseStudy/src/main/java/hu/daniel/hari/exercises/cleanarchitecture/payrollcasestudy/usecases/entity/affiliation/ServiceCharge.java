package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation;

import java.time.LocalDate;

public interface ServiceCharge {

	int getAmount();
	LocalDate getDate();
	
	void setAmount(int amount);
	void setDate(LocalDate date);

	public static interface ServiceChargeFactory {
		ServiceCharge serviceCharge(LocalDate date, int amount);
	}

}
