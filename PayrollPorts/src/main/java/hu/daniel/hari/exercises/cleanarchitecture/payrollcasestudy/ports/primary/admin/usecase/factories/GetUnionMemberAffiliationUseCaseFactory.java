package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.FunctionUseCase;

public interface GetUnionMemberAffiliationUseCaseFactory {
	FunctionUseCase<GetUnionMemberAffiliationRequest, GetUnionMemberAffiliationResponse> getUnionMemberAffiliationUseCase();
}