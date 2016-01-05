package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;

public class RemoveUnionMemberAffiliationUseCase extends TransactionalUseCase<RemoveUnionMemberAffiliationRequest> {
	
	private AffiliationFactory affiliationFactory;

	public RemoveUnionMemberAffiliationUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			AffiliationFactory affiliationFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.affiliationFactory = affiliationFactory;
	}

	@Override
	protected void executeInTransaction(RemoveUnionMemberAffiliationRequest request) {
		getEmployeeByUnionMemberId(request.unionMemberId).setAffiliation(affiliationFactory.noAffiliation());
	}

	private Employee getEmployeeByUnionMemberId(int unionMemberId) {
		return employeeGateway.findById(employeeGateway.findByUnionMemberId(unionMemberId));
	}

	public static interface RemoveUnionMemberAffiliationUseCaseFactory {
		RemoveUnionMemberAffiliationUseCase removeUnionMemberAffiliationUseCase();
	}

}
