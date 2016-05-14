package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface ChangeToAbstractPaymentMethodUseCaseFactory {
	 CommandUseCase<ChangeToDirectPaymentMethodRequest> changeToDirectPaymentMethodUseCase(); 
	 CommandUseCase<ChangeEmployeeRequest> changeToPaymasterPaymentMethodUseCase(); 
}