package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.sendpay;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.sendpay.interactor.SendPayInteractorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.SendPayUseCaseRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class SendPayUseCase extends TransactionalUseCase<SendPayUseCaseRequest> {

	private SendPayInteractorFactory sendPayInteractorFactory;

	public SendPayUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway,
			SendPayInteractorFactory sendPayInteractorFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.sendPayInteractorFactory = sendPayInteractorFactory;
	}

	//TODO: Transactional has no meaning here
	@Override
	protected void executeInTransaction(SendPayUseCaseRequest request) {
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
