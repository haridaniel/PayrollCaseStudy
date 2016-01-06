package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentmethod.JPADirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.DirectPaymentMethod;

@AutoBindedProxy(JPADirectPaymentMethod.class)
public class DirectPaymentMethodProxy extends DirectPaymentMethod implements PaymentMethodProxy {

	private JPADirectPaymentMethod jpaDirectPaymentMethod;

	public DirectPaymentMethodProxy(JPADirectPaymentMethod jpaDirectPaymentMethod) {
		this.jpaDirectPaymentMethod = jpaDirectPaymentMethod;
	}
	
	@Override
	public String getAccountNumber() {
		return jpaDirectPaymentMethod.getAccountNumber();
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		jpaDirectPaymentMethod.setAccountNumber(accountNumber);
	}

	@Override
	public JPAPaymentMethod getJPAObject() {
		return jpaDirectPaymentMethod;
	}

}
