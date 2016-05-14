package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;

public interface ChangeToAbstractPaymentMethodUseCaseFactory {
	 CommandUseCase<ChangeToDirectPaymentMethodRequest> changeToDirectPaymentMethodUseCase(); 
	 CommandUseCase<ChangeEmployeeRequest> changeToPaymasterPaymentMethodUseCase(); 
}