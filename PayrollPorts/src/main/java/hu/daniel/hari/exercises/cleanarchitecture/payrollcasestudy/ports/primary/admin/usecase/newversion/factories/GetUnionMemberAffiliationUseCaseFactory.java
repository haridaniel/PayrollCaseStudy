package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.FunctionUseCase;

public interface GetUnionMemberAffiliationUseCaseFactory {
	FunctionUseCase<GetUnionMemberAffiliationRequest, GetUnionMemberAffiliationResponse> getUnionMemberAffiliationUseCase();
}