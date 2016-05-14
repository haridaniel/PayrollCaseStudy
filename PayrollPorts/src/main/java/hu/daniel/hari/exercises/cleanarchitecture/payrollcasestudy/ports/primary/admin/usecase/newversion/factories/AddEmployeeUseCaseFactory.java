package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface AddEmployeeUseCaseFactory {
	CommandUseCase<AddSalariedEmployeeRequest> addSalariedEmployeeUseCase();
	CommandUseCase<AddHourlyEmployeeRequest> addHourlyEmployeeUseCase();
	CommandUseCase<AddCommissionedEmployeeRequest> addCommissionedEmployeeUseCase();
}