package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddSalariedEmployeeRequest;

public interface AddEmployeeUseCaseFactory {
	CommandUseCase<AddSalariedEmployeeRequest> addSalariedEmployeeUseCase();
	CommandUseCase<AddHourlyEmployeeRequest> addHourlyEmployeeUseCase();
	CommandUseCase<AddCommissionedEmployeeRequest> addCommissionedEmployeeUseCase();
}