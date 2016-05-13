package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.AddTimeCardUseCase;

public interface AddTimeCardUseCaseFactory {
	AddTimeCardUseCase addTimeCardUseCase();
}