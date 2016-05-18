package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.DeleteEmployeeRequest;

public interface DeleteEmployeeUseCaseFactory {
	CommandUseCase<DeleteEmployeeRequest> deleteEmployeeUseCase();
}
