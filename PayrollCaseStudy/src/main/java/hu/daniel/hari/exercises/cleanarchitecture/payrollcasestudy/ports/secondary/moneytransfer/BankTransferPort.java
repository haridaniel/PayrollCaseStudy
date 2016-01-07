package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.interactor.port;

public interface BankTransferPort {
	void pay(int amount, String accountNumber);
}
