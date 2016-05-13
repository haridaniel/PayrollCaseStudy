package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;

public interface GetEmployeeUseCaseFactory extends UseCaseFactory {
	GetEmployeeUseCase getEmployeeUseCase();
}