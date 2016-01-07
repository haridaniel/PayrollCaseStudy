package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.sendpay.interactor;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer.BankTransferPort;

public class SendPayInteractorFactory {

	private BankTransferPort bankTransferPort;

	public SendPayInteractorFactory(
			BankTransferPort bankTransferPort
			) {
		this.bankTransferPort = bankTransferPort;
	}

	public <T extends PaymentMethod> SendPayInteractor createFor(T paymentMethod) {
		if (paymentMethod instanceof HoldPaymentMethod)
			return new HoldMethodSendPayInteractor();
		else if (paymentMethod instanceof DirectPaymentMethod)
			return new DirectMethodSendPayInteractor(bankTransferPort, (DirectPaymentMethod) paymentMethod);
		else
			throw new IllegalArgumentException();
	}
}
