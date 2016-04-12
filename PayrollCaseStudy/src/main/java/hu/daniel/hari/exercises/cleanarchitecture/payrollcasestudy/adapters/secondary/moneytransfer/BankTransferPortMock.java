package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.moneytransfer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer.BankTransferPort;

public class BankTransferPortMock implements BankTransferPort {
	private Log log = LogFactory.getLog(getClass());

	@Override
	public void pay(int amount, String accountNumber) {
		log.info(String.format("(Mock) Amount %s transferred to account %s", amount, accountNumber));
	}

}
