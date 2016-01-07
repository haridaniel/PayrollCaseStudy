package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.OnceExecutableUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor.SendPayInteractorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.SendPayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class SendPayUseCase extends OnceExecutableUseCase<SendPayRequest> {

	private EmployeeGateway employeeGateway;
	private SendPayInteractorFactory sendPayInteractorFactory;

	public SendPayUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway,
			SendPayInteractorFactory sendPayInteractorFactory
			) {
		super();
		this.employeeGateway = employeeGateway;
		this.sendPayInteractorFactory = sendPayInteractorFactory;
	}
	
	@Override
	protected void executeOnce(SendPayRequest request) {
		for (PayCheck payCheck : request.payChecks) {
			pay(payCheck);
		}
	}

	private void pay(PayCheck payCheck) {
		pay(payCheck.getNetAmount(), getEmployee(payCheck.getEmployeeId()).getPaymentMethod());
	}

	private void pay(int amount, PaymentMethod paymentMethod) {
		sendPayInteractorFactory.createFor(paymentMethod).pay(amount);
	}

	private Employee getEmployee(int employeeId) {
		return employeeGateway.findById(employeeId);
	}

	public static interface SendPayUseCaseFactory {
		SendPayUseCase sendPayUseCase();
	}

	
}
