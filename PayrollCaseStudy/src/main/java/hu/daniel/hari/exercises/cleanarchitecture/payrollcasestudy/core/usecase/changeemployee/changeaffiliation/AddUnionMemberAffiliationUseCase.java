package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.ChangeEmployeeUseCase;

public class AddUnionMemberAffiliationUseCase extends ChangeEmployeeUseCase<AddUnionMemberAffiliationRequest> {

	public AddUnionMemberAffiliationUseCase(Database database) {
		super(database);
	}

	@Override
	protected void change(AddUnionMemberAffiliationRequest request, Employee employee) {
		employee.setAffiliation(entityGateway.factory().unionMemberAffiliation(request.unionMemberId, request.weeklyDueAmount));
	}
	
}
