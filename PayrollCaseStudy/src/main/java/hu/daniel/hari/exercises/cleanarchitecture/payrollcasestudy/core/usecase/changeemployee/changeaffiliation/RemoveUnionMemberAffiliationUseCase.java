package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public class RemoveUnionMemberAffiliationUseCase extends TransactionalUseCase<RemoveUnionMemberAffiliationRequest> {
	
	public RemoveUnionMemberAffiliationUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(RemoveUnionMemberAffiliationRequest request) {
		getEmployeeByUnionMemberId(request.unionMemberId).setAffiliation(entityGateway.factory().noAffiliation());
	}

	private Employee getEmployeeByUnionMemberId(int unionMemberId) {
		return entityGateway.getEmployee(entityGateway.getEmployeeIdByUnionMemberId(unionMemberId));
	}

	
}
