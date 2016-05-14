package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface RemoveUnionMemberAffiliationUseCaseFactory {
	CommandUseCase<RemoveUnionMemberAffiliationRequest> removeUnionMemberAffiliationUseCase();
}