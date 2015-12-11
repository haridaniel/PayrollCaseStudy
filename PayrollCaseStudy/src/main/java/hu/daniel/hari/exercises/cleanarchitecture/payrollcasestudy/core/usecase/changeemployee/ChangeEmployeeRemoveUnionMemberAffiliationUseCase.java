package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalDatabaseUseCase;

public class ChangeEmployeeRemoveUnionMemberAffiliationUseCase extends TransactionalDatabaseUseCase {
	
	private int unionMemberId;

	public ChangeEmployeeRemoveUnionMemberAffiliationUseCase(PayrollDatabase database, int unionMemberId) {
		super(database);
		this.unionMemberId = unionMemberId;
	}

	@Override
	protected void executeInTransaction() {
		getEmployeeByUnionMemberId().setAffiliation(new NoAffiliation());
	}

	private Employee getEmployeeByUnionMemberId() {
		return payrollDatabase.getEmployee(payrollDatabase.getEmployeeIdByUnionMemberId(unionMemberId));
	}

	
}
