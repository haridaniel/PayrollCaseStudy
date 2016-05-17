package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalariedPaymentType;

public class SalariedPaymentTypeImpl extends SalariedPaymentType {

	private int monthlySalary;

	public SalariedPaymentTypeImpl(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public int getMonthlySalary() {
		return monthlySalary;
	}

	@Override
	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

}
