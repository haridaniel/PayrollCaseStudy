package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.fullfillers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;

public class DirectPaymentFulfiller implements PaymentFulfiller {

	private BankTransferPort bankTransferPort;
	private DirectPaymentMethod directPaymentMethod;

	public DirectPaymentFulfiller(
			BankTransferPort bankTransferPort,
			DirectPaymentMethod directPaymentMethod
			) {
		this.bankTransferPort = bankTransferPort;
		this.directPaymentMethod = directPaymentMethod;
	}

	@Override
	public void fulfillPayment(PayCheck payCheck) {
		bankTransferPort.pay(payCheck.getNetAmount(), directPaymentMethod.getAccountNumber());
	}


}
