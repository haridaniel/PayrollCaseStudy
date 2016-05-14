package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.UpdateTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface UpdateTimeCardUseCaseFactory {
	CommandUseCase<UpdateTimeCardRequest> updateTimeCardUseCase();
}