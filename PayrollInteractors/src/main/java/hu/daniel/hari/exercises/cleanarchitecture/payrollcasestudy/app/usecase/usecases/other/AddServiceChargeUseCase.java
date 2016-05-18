package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.other;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.ServiceCharge.ServiceChargeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.other.AddSalesReceiptUseCase.TriedToAddSalesReceiptToNonCommissionedEmployeeException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddServiceChargeUseCase extends EmployeeGatewayCommandUseCase<AddServiceChargeRequest> {

	private ServiceChargeFactory serviceChargeFactory;

	public AddServiceChargeUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			ServiceChargeFactory serviceChargeFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.serviceChargeFactory = serviceChargeFactory;
	}

	@Override
	protected void executeInTransaction(AddServiceChargeRequest request) {
		Employee employee = getEmployeeByUnionMemberId(request.unionMemberId);
		
		UnionMemberAffiliation unionMemberAffiliation = castUnionMemberAffiliation(employee.getAffiliation());
		unionMemberAffiliation.addServiceCharge(createServiceCharge(request));
	}


	private Employee getEmployeeByUnionMemberId(int unionMemberId) {
		return employeeGateway.findById(employeeGateway.findEmployeeIdByUnionMemberId(unionMemberId));
	}

	private UnionMemberAffiliation castUnionMemberAffiliation(Affiliation affiliation) {
		if(affiliation instanceof UnionMemberAffiliation) {
			return (UnionMemberAffiliation) affiliation;
		} else {
			throw new TriedToAddSalesReceiptToNonCommissionedEmployeeException();
		}
	}

	private ServiceCharge createServiceCharge(AddServiceChargeRequest request) {
		return serviceChargeFactory.serviceCharge(request.date, request.amount);
	}
	
}
