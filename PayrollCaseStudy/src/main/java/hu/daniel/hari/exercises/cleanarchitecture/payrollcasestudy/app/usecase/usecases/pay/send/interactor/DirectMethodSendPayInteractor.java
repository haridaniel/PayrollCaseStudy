package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer.BankTransferPort;

public class DirectMethodSendPayInteractor implements SendPayInteractor {

	private BankTransferPort bankTransferPort;
	private DirectPaymentMethod directPaymentMethod;

	public DirectMethodSendPayInteractor(BankTransferPort bankTransferPort, DirectPaymentMethod directPaymentMethod) {
		this.bankTransferPort = bankTransferPort;
		this.directPaymentMethod = directPaymentMethod;
	}
	
	@Override
	public void pay(int amount) {
		bankTransferPort.pay(amount, directPaymentMethod.getAccountNumber());
	}

}
