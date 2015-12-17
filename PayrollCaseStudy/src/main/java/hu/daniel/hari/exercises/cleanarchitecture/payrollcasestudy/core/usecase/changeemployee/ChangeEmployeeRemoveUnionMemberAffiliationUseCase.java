package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public class ChangeEmployeeRemoveUnionMemberAffiliationUseCase extends TransactionalUseCase {
	
	private int unionMemberId;

	public ChangeEmployeeRemoveUnionMemberAffiliationUseCase(Database database, int unionMemberId) {
		super(database);
		this.unionMemberId = unionMemberId;
	}

	@Override
	protected void executeInTransaction() {
		getEmployeeByUnionMemberId().setAffiliation(entityGateway.factory().noAffiliation());
	}

	private Employee getEmployeeByUnionMemberId() {
		return entityGateway.getEmployee(entityGateway.getEmployeeIdByUnionMemberId(unionMemberId));
	}

	
}
