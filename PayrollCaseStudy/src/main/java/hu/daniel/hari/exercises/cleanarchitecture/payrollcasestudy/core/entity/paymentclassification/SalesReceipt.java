package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.LocalDate;

public abstract class SalesReceipt {

	public abstract int getAmount();
	public abstract LocalDate getDate();
	
	public abstract void setAmount(int amount);
	public abstract void setDate(LocalDate date);

	public static interface SalesReceiptFactory {
		SalesReceipt salesReceipt(LocalDate date, int amount);
	}	
	
}
