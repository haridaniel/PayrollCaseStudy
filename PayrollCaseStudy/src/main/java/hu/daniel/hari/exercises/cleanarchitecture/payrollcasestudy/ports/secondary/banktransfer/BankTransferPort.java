package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer;

public interface BankTransferPort {
	void pay(int amount, String accountNumber);
}
