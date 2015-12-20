package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public class AddUnionMemberAffiliationUseCase extends TransactionalUseCase<AddUnionMemberAffiliationRequest> {

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
	protected void executeInTransaction(AddUnionMemberAffiliationRequest request) {
		Employee employee = employeeGateway.getEmployee(request.employeeId);
		employee.setAffiliation(affiliationFactory.unionMemberAffiliation(request.unionMemberId, request.weeklyDueAmount));
	}

	public static interface AddUnionMemberAffiliationUseCaseFactory {
		AddUnionMemberAffiliationUseCase addUnionMemberAffiliationUseCase();
	}

}
