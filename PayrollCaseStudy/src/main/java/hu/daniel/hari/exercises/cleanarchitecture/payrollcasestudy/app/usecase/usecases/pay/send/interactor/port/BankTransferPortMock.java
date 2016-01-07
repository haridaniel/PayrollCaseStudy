package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor.port;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer.BankTransferPort;

public class BankTransferPortMock implements BankTransferPort {

	@Override
	public void pay(int amount, String accountNumber) {
		System.out.println("MOCKED pay:" + amount + " to " + accountNumber);
	}

}
