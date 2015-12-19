package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

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
		return employeeGateway.getEmployee(employeeGateway.getEmployeeIdByUnionMemberId(unionMemberId));
	}

	
}
