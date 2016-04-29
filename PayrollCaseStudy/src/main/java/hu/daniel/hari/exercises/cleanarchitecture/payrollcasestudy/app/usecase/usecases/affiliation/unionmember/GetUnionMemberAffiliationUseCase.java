package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetUnionMemberAffiliationUseCase extends 
	TransactionalEmployeeGatewayUseCase<GetUnionMemberAffiliationRequest> implements
	HasResponse<GetUnionMemberAffiliationResponse>
{
	private GetUnionMemberAffiliationResponse response;

	public GetUnionMemberAffiliationUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GetUnionMemberAffiliationRequest request) {
		//TODO: clean
		
		Employee employee = employeeGateway.findById(request.employeeId);
		Affiliation affiliation = employee.getAffiliation();
		UnionMemberAffiliation unionMemberAffiliation = (UnionMemberAffiliation) affiliation;
		unionMemberAffiliation.getUnionMemberId();
		
		GetUnionMemberAffiliationResponse response = new GetUnionMemberAffiliationResponse();
		response.employeeId = employee.getId();
		response.unionMemberId = unionMemberAffiliation.getUnionMemberId();
		response.weeklyDueAmount = unionMemberAffiliation.getWeeklyDueAmount();
		this.response = response; 
	}

	@Override
	public GetUnionMemberAffiliationResponse getResponse() {
		return response;
	}

	public static interface GetUnionMemberAffiliationUseCaseFactory extends UseCaseFactory {
		GetUnionMemberAffiliationUseCase getUnionMemberAffiliationUseCase();
	}
	
}
