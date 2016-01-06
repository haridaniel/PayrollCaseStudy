package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.interactor.port;

public class BankTransferPortMock implements BankTransferPort {

	@Override
	public void pay(int amount, String accountNumber) {
		System.out.println("MOCKED pay:" + amount + " to " + accountNumber);
	}

}
