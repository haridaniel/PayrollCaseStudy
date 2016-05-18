package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.affiliation.unionmember.UnionMemberIdAlreadyExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddUnionMemberAffiliationUseCase extends EmployeeGatewayCommandUseCase<AddUnionMemberAffiliationRequest> {

	private AffiliationFactory affiliationFactory;
	private AddUnionMemberAffiliationRequest request;

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
		this.request = request;
		new Validator();
		employeeGateway.findById(request.employeeId)
			.setAffiliation(affiliationFactory.unionMemberAffiliation(request.unionMemberId, request.weeklyDueAmount));
	}

	private final class Validator  {
		public Validator() {
			validateUnionMemberIdNotExists(request.unionMemberId);
		}
	
		private void validateUnionMemberIdNotExists(int unionMemberId) {
			if(employeeGateway.isEmployeeExistsByUnionMemberId(unionMemberId)) {
				Employee ownerEmployee = employeeGateway.findById(employeeGateway.findEmployeeIdByUnionMemberId(unionMemberId));
				throw new UnionMemberIdAlreadyExistsException(ownerEmployee.getId(), ownerEmployee.getName());
			}
		}
	}
	
	
}
