package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.GetUnionMemberAffiliationUseCase;

public interface GetUnionMemberAffiliationUseCaseFactory extends UseCaseFactory {
	GetUnionMemberAffiliationUseCase getUnionMemberAffiliationUseCase();
}