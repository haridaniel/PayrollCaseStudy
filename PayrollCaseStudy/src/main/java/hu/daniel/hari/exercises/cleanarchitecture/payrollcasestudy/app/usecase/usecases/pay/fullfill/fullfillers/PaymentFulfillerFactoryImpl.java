package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.fullfillers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymasterPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class PaymentFulfillerFactoryImpl implements PaymentFulfillerFactory {
	private BankTransferPort bankTransferPort;
	private TransactionalRunner transactionalRunner;

	public PaymentFulfillerFactoryImpl(
			BankTransferPort bankTransferPort,
			TransactionalRunner transactionalRunner
			) {
		this.bankTransferPort = bankTransferPort;
		this.transactionalRunner = transactionalRunner;
	}

	@Override
	public PaymentFulfiller visit(PaymasterPaymentMethod paymasterPaymentMethod) {
		return new PaymasterPaymentFulfiller(transactionalRunner);
	}

	@Override
	public PaymentFulfiller visit(DirectPaymentMethod directPaymentMethod) {
		return new DirectPaymentFulfiller(bankTransferPort, directPaymentMethod);
	}

}
