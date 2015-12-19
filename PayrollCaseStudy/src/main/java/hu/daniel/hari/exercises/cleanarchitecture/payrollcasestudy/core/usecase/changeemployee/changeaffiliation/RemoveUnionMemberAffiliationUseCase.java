package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class RemoveUnionMemberAffiliationUseCase extends TransactionalUseCase<RemoveUnionMemberAffiliationRequest> {
	
	private AffiliationFactory affiliationFactory;

	public RemoveUnionMemberAffiliationUseCase(Database database, AffiliationFactory affiliationFactory) {
		super(database);
		this.affiliationFactory = affiliationFactory;
	}

	@Override
	protected void executeInTransaction(RemoveUnionMemberAffiliationRequest request) {
		getEmployeeByUnionMemberId(request.unionMemberId).setAffiliation(affiliationFactory.noAffiliation());
	}

	private Employee getEmployeeByUnionMemberId(int unionMemberId) {
		return entityGateway.getEmployee(entityGateway.getEmployeeIdByUnionMemberId(unionMemberId));
	}

	
}
