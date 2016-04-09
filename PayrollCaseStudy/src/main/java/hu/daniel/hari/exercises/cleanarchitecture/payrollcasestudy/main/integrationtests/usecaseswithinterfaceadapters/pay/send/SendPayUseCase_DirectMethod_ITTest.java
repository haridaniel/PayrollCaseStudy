package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.send;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.SendPayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor.SendPayInteractorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.SendPayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer.BankTransferPort;

public class SendPayUseCase_DirectMethod_ITTest extends AbstractUseCaseITTest {

	private int employeeId = 1;
	private int netAmount = 100;
	private String accountNumber = "11111-1000203";

	private BankTransferPort bankTransferPortSpy = mock(BankTransferPort.class);

	private SendPayUseCase sendPayUseCase = new SendPayUseCase(
			database.transactionalRunner(), 
			database.employeeGateway(), 
			new SendPayInteractorFactory(bankTransferPortSpy));


	public SendPayUseCase_DirectMethod_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testSendPayment_DirectMethod() throws Exception {
		givenAnEmployeeWithDirectPaymentMethod();
		whenSendPaymentUseCase(givenPayCheckWithNetAmount(netAmount));
		thenBankTransferPortShouldBeCalled();
	}

	private void givenAnEmployeeWithDirectPaymentMethod() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
		useCaseFactories.changeToDirectPaymentMethodUseCase().execute(new ChangeToDirectPaymentMethodRequest(employeeId, accountNumber));
	}

	private PayCheck givenPayCheckWithNetAmount(int netAmount) {
		return new PayCheck(null, employeeId, netAmount, 0, netAmount);
	}

	private void whenSendPaymentUseCase(PayCheck payCheck) {
		sendPayUseCase.execute(new SendPayRequest(Arrays.asList(payCheck)));
	}

	private void thenBankTransferPortShouldBeCalled() {
		verify(bankTransferPortSpy).pay(netAmount, accountNumber);
	}
	

}