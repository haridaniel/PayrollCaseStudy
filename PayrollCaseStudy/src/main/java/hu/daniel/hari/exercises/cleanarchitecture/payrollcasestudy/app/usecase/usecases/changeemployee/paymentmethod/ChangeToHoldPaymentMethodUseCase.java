package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.ChangeEmployeeRequest;

public class ChangeToHoldPaymentMethodUseCase extends ChangeToAbstractPaymentMethodUseCase<ChangeEmployeeRequest> {

	private PaymentMethodFactory paymentMethodFactory;

	public ChangeToHoldPaymentMethodUseCase(
			TransactionalRunner transactionalRunner,
			EmployeeGateway employeeGateway,
			PaymentMethodFactory paymentMethodFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.paymentMethodFactory = paymentMethodFactory;
	}

	@Override
	protected PaymentMethod getPaymentMethod(ChangeEmployeeRequest request) {
		return paymentMethodFactory.holdPaymentMethod();
	}


}
