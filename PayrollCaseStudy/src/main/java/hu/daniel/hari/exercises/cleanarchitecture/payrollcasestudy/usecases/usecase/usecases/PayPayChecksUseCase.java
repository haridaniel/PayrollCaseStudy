package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.PaymentFulFiller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.PaymentFulFillerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.PaymentFulFillerSelector;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.direct.DirectPaymentFulFiller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.PayPayChecksUseCaseRequest;

public class PayPayChecksUseCase extends TransactionalUseCase<PayPayChecksUseCaseRequest> {

	private PaymentFulFillerSelector paymentFulFillerSelector;

	public PayPayChecksUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway,
			DirectPaymentFulFiller directPaymentFulFiller
			) {
		super(transactionalRunner, employeeGateway);
		this.paymentFulFillerSelector = new PaymentFulFillerSelector(directPaymentFulFiller);
	}

	//TODO: Transactional has no meaning here
	@Override
	protected void executeInTransaction(PayPayChecksUseCaseRequest request) {
		for (PayCheck payCheck : request.payChecks) {
			pay(payCheck);
		}
	}

	private void pay(PayCheck payCheck) {
		System.out.println("PAY");
		Employee employee = employeeGateway.findById(payCheck.getEmployeeId());
		PaymentFulFiller paymentFulFiller = paymentFulFillerSelector.forPaymentMethodType(employee.getPaymentMethod().getClass());
		paymentFulFiller.pay(payCheck.getNetAmount());
	}

	public static interface PayPayChecksUseCaseFactory {
		PayPayChecksUseCase payPayChecksUseCase();
	}

	
}
