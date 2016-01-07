package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.ChangeEmployeeRequest;

public class ChangeToDirectPaymentMethodUseCaseRequest extends ChangeEmployeeRequest {

	public String accountNumber;

	public ChangeToDirectPaymentMethodUseCaseRequest(int employeeId, String accountNumber) {
		super(employeeId);
		this.accountNumber = accountNumber;
	}

}
