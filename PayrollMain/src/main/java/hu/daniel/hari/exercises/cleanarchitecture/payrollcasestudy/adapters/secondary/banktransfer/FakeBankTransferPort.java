package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.banktransfer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;

public class FakeBankTransferPort implements BankTransferPort {
	private Log log = LogFactory.getLog(getClass());

	@Override
	public void pay(int amount, String accountNumber) {
		log.info(String.format("(Fake) Amount %s transferred to account %s", amount, accountNumber));
	}

}
