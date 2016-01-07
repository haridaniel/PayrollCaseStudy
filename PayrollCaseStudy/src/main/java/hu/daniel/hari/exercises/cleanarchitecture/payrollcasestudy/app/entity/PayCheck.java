package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity;

public class PayCheck {
	
	private int employeeId;
	private final int grossAmount;
	private int deductionsAmount;
	
	public PayCheck(int employeeId, int grossAmount, int deductionsAmount) {
		this.employeeId = employeeId;
		this.grossAmount = grossAmount;
		this.deductionsAmount = deductionsAmount;
	}

	public int getEmployeeId() {
		return employeeId;
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
