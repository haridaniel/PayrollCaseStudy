package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.ChangeEmployeeNameUseCase;

public interface ChangeEmployeeUseCaseFactory {
	ChangeEmployeeNameUseCase changeEmployeeNameUseCase();
}