package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;

public interface ChangeToAbstractPaymentMethodUseCaseFactory {
	 CommandUseCase<ChangeToDirectPaymentMethodRequest> changeToDirectPaymentMethodUseCase(); 
	 CommandUseCase<ChangeEmployeeRequest> changeToPaymasterPaymentMethodUseCase(); 
}