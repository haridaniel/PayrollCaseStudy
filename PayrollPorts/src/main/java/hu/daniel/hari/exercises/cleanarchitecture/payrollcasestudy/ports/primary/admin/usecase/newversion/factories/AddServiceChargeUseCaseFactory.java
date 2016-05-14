package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface AddServiceChargeUseCaseFactory {
	CommandUseCase<AddServiceChargeRequest> addServiceChargeUseCase();
}