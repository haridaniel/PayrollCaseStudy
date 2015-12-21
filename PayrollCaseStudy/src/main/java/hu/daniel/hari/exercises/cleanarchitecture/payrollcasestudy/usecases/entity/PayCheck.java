package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity;

public class PayCheck {
	
	private final int grossAmount;
	private int deductionsAmount;
	
	public PayCheck(int grossAmount, int deductionsAmount) {
		this.grossAmount = grossAmount;
		this.deductionsAmount = deductionsAmount;
	}

	public int getGrossAmount() {
		return grossAmount;
	}
	
	public int getDeductionsAmount() {
		return deductionsAmount;
	}
	
	public int getNetAmount() {
		return grossAmount - deductionsAmount;
	}
	
}
