package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;

public class AddUnionMemberAffiliationUseCase extends TransactionalEmployeeUseCase<AddUnionMemberAffiliationRequest> {

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
