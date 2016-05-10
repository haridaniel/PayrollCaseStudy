package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeRequest;

public class ChangeToDirectPaymentMethodRequest extends ChangeEmployeeRequest {

	public String accountNumber;

	public ChangeToDirectPaymentMethodRequest(int employeeId, String accountNumber) {
		super(employeeId);
		this.accountNumber = accountNumber;
	}

}
