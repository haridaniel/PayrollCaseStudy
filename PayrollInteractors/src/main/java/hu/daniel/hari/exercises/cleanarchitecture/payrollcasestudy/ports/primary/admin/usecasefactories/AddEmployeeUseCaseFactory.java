package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;

public interface AddEmployeeUseCaseFactory {
	AddSalariedEmployeeUseCase addSalariedEmployeeUseCase();
	AddHourlyEmployeeUseCase addHourlyEmployeeUseCase();
	AddCommissionedEmployeeUseCase addCommissionedEmployeeUseCase();
}