package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeAddUnionMemberAffiliationUseCase extends ChangeEmployeeUseCase {

	private int unionMemberId;
	private int weeklyDueAmount;

	public ChangeEmployeeAddUnionMemberAffiliationUseCase(Database database, int employeeId, int unionMemberId, int weeklyDueAmount) {
		super(database, employeeId);
		this.unionMemberId = unionMemberId;
		this.weeklyDueAmount = weeklyDueAmount;
	}

	@Override
	protected void change(Employee employee) {
		employee.setAffiliation(entityGateway.factory().unionMemberAffiliation(unionMemberId, weeklyDueAmount));
	}
	
}
