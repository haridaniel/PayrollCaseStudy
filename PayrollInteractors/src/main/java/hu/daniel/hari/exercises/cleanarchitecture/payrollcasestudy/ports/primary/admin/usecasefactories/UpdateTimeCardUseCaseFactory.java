package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.UpdateTimeCardUseCase;

public interface UpdateTimeCardUseCaseFactory {
	UpdateTimeCardUseCase updateTimeCardUseCase();
}