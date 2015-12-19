package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.ChangeEmployeeUseCase;

public class AddUnionMemberAffiliationUseCase extends ChangeEmployeeUseCase<AddUnionMemberAffiliationRequest> {

	private AffiliationFactory affiliationFactory;

	public AddUnionMemberAffiliationUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			AffiliationFactory affiliationFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.affiliationFactory = affiliationFactory;
	}

	@Override
	protected void change(AddUnionMemberAffiliationRequest request, Employee employee) {
		employee.setAffiliation(affiliationFactory.unionMemberAffiliation(request.unionMemberId, request.weeklyDueAmount));
	}
	
}
