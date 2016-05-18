package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.Response.EmptyResponse;

public interface DeleteEmployeeUseCaseFactory {
	CommandUseCase<DeleteEmployeeRequest> deleteEmployeeUseCase();
}
