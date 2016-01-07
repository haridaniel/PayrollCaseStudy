package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodUseCaseRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

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
