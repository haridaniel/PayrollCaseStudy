package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodUseCaseRequest;

public class ChangeToDirectPaymentMethodUseCase extends ChangeToAbstractPaymentMethodUseCase<ChangeToDirectPaymentMethodUseCaseRequest> {

	private PaymentMethodFactory paymentMethodFactory;

	public ChangeToDirectPaymentMethodUseCase(
			TransactionalRunner transactionalRunner,
			EmployeeGateway employeeGateway,
			PaymentMethodFactory paymentMethodFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.paymentMethodFactory = paymentMethodFactory;
	}

	@Override
	protected PaymentMethod getPaymentMethod(ChangeToDirectPaymentMethodUseCaseRequest request) {
		return paymentMethodFactory.directPaymentMethod(request.accountNumber);
	}


}
