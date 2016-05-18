package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.CommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;

public interface RemoveUnionMemberAffiliationUseCaseFactory {
	CommandUseCase<RemoveUnionMemberAffiliationRequest> removeUnionMemberAffiliationUseCase();
}