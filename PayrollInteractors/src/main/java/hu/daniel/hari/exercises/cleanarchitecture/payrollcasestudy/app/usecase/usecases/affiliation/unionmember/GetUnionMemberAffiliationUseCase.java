package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayFunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetUnionMemberAffiliationUseCase extends 
	EmployeeGatewayFunctionUseCase<GetUnionMemberAffiliationRequest, GetUnionMemberAffiliationResponse>
{

	public GetUnionMemberAffiliationUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected GetUnionMemberAffiliationResponse executeInTransaction(GetUnionMemberAffiliationRequest request) {
		Employee employee = employeeGateway.findById(request.employeeId);
		return toResponse(employee, getUnionMemberAffiliation(employee)); 
	}

	private UnionMemberAffiliation getUnionMemberAffiliation(Employee employee) {
		Affiliation affiliation = employee.getAffiliation();
		if(affiliation instanceof UnionMemberAffiliation)
			return (UnionMemberAffiliation) affiliation;
		else
			throw new NotUnionMemberException();
	}

	private GetUnionMemberAffiliationResponse toResponse(Employee employee, UnionMemberAffiliation unionMemberAffiliation) {
		GetUnionMemberAffiliationResponse response = new GetUnionMemberAffiliationResponse();
		response.employeeId = employee.getId();
		response.unionMemberId = unionMemberAffiliation.getUnionMemberId();
		response.weeklyDueAmount = unionMemberAffiliation.getWeeklyDueAmount();
		return response;
	}

	public static class NotUnionMemberException extends RuntimeException {
	}
	
}
