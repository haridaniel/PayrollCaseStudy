package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod;

import javax.persistence.Entity;

@Entity
public class JPADirectPaymentMethod extends JPAPaymentMethod {

	private String accountNumber;
	
	public JPADirectPaymentMethod() {}
	public JPADirectPaymentMethod(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
