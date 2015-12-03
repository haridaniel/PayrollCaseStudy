package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

public class PayCheck {
	
	//TODO: ki lesz véve, only debug
	public int employeeId;
	
	public int amount;
	
	public PayCheck(int employeeId, int amount) {
		this.employeeId = employeeId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PayCheck [employeeId=" + employeeId + ", amount=" + amount + "]";
	}
	
}
