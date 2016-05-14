package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface AddSalesReceiptUseCaseFactory {
	CommandUseCase<AddSalesReceiptRequest> addSalesReceiptUseCaseFactory();
}