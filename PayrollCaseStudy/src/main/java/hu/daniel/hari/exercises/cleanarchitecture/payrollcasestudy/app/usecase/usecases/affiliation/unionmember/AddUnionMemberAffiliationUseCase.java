package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddUnionMemberAffiliationUseCase extends TransactionalEmployeeGatewayUseCase<AddUnionMemberAffiliationRequest> {

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
		Employee employee = employeeGateway.findById(request.employeeId);
		employee.setAffiliation(affiliationFactory.unionMemberAffiliation(request.unionMemberId, request.weeklyDueAmount));
	}

	public static interface AddUnionMemberAffiliationUseCaseFactory {
		AddUnionMemberAffiliationUseCase addUnionMemberAffiliationUseCase();
	}

}
