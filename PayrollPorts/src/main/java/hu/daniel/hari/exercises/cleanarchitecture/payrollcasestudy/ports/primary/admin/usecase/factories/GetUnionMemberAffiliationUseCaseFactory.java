package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.FunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;

public interface GetUnionMemberAffiliationUseCaseFactory {
	FunctionUseCase<GetUnionMemberAffiliationRequest, GetUnionMemberAffiliationResponse> getUnionMemberAffiliationUseCase();
}