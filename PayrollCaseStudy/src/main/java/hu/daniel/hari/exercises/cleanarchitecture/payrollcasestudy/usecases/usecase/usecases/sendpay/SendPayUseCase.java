package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.interactor.SendPayInteractorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.SendPayUseCaseRequest;

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
