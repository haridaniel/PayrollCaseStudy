package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.interactor;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.interactor.port.BankTransferPort;

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
