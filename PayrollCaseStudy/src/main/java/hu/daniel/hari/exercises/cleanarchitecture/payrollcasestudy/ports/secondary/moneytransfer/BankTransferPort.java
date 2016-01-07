package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer;

public interface BankTransferPort {
	void pay(int amount, String accountNumber);
}
