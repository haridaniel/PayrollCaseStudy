package hu.daniel.hari.testthis.data.inmemory.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;

public class DirectPaymentMethodImpl extends DirectPaymentMethod {
	
	private String accountNumber;

	public DirectPaymentMethodImpl(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
