package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge.ServiceChargeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase.TriedToAddSalesReceiptToNonCommissionedEmployeeException;

public class AddServiceChargeUseCase extends TransactionalUseCase<AddServiceChargeRequest> {

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
		return employeeGateway.getEmployee(employeeGateway.getEmployeeIdByUnionMemberId(unionMemberId));
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

	public static interface AddServiceChargeUseCaseFactory {
		AddServiceChargeUseCase addServiceChargeUseCase();
	}
	
}
