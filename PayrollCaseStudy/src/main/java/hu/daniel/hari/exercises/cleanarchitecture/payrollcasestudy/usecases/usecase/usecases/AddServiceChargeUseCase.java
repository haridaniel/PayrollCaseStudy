package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.ServiceCharge.ServiceChargeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.AddSalesReceiptUseCase.TriedToAddSalesReceiptToNonCommissionedEmployeeException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.AddServiceChargeRequest;

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
		return employeeGateway.findById(employeeGateway.findByUnionMemberId(unionMemberId));
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
