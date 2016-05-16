package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecases.pay.fulfill;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PaymentFulfillRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.AbstractMultipleDatabaseUseCaseTest;

public class PaymentFulfillUseCase_DirectMethod_ITTest extends AbstractMultipleDatabaseUseCaseTest {
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	
	private int employeeId = 1;
	private int monthlySalary = 1001;
	private String accountNumber = "11111-1000203";
	private LocalDate payDate = LAST_DAY_OF_A_MONTH;

	private BankTransferPort bankTransferPortSpy = mock(BankTransferPort.class);

	private PaymentFulfillUseCase paymentFulfillUseCase = new PaymentFulfillUseCase(
			database.employeeGateway(), 
			database.transactionalRunner(),
			bankTransferPortSpy
			);

	public PaymentFulfillUseCase_DirectMethod_ITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	@Test
	public void testSendPayment_DirectMethod() throws Exception {
		givenAnEmployeeWithDirectPaymentMethod();
		whenSendPaymentUseCase();
		thenBankTransferPortShouldBeCalled();
	}

	private void givenAnEmployeeWithDirectPaymentMethod() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", monthlySalary));
		useCaseFactories.changeToDirectPaymentMethodUseCase().execute(new ChangeToDirectPaymentMethodRequest(employeeId, accountNumber));
	}

	private void whenSendPaymentUseCase() {
		paymentFulfillUseCase.execute(new PaymentFulfillRequest(payDate));
	}

	private void thenBankTransferPortShouldBeCalled() {
		verify(bankTransferPortSpy).pay(monthlySalary, accountNumber);
	}

}