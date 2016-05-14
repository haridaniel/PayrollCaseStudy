package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.CommandUseCase;

public interface AddUnionMemberAffiliationUseCaseFactory {
	CommandUseCase<AddUnionMemberAffiliationRequest> addUnionMemberAffiliationUseCase();
}