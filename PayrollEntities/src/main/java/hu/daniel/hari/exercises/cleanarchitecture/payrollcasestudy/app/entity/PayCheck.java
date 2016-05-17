package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity;

import java.time.LocalDate;

public class PayCheck {

	private LocalDate date;
	private int employeeId;
	private int grossAmount;
	private int deductionsAmount;
	private int netAmount;
	
	public PayCheck(LocalDate payDate, int employeeId, int grossAmount, int deductionsAmount, int netAmount) {
		this.date = payDate;
		this.employeeId = employeeId;
		this.grossAmount = grossAmount;
		this.deductionsAmount = deductionsAmount;
		this.netAmount = netAmount;
	}

	public LocalDate getDate() {
		return date;
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
		return netAmount;
	}

	@Override
	public String toString() {
		return "PayCheck [date=" + date + ", employeeId=" + employeeId + ", grossAmount=" + grossAmount
				+ ", deductionsAmount=" + deductionsAmount + ", netAmount=" + netAmount + "]";
	}
	
	
}
