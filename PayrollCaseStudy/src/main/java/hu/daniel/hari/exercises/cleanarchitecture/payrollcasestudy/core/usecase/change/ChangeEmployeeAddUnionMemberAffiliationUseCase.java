package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.change;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class ChangeEmployeeAddUnionMemberAffiliationUseCase extends ChangeEmployeeUseCase {

	private int unionMemberId;
	private int weeklyDueAmount;

	public ChangeEmployeeAddUnionMemberAffiliationUseCase(PayrollDatabase payrollDatabase, int employeeId, int unionMemberId, int weeklyDueAmount) {
		super(payrollDatabase, employeeId);
		this.unionMemberId = unionMemberId;
		this.weeklyDueAmount = weeklyDueAmount;
	}

	@Override
	protected void change(Employee employee) {
		employee.setAffiliation(payrollDatabase.factory().unionMemberAffiliation(unionMemberId, weeklyDueAmount));
	}
	
}
