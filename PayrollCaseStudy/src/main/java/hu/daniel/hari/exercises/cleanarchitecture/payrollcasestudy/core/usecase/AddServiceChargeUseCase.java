package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase.TriedToAddSalesReceiptToNonCommissionedEmployeeException;

public class AddServiceChargeUseCase extends TransactionalUseCase<AddServiceChargeRequest> {

	public AddServiceChargeUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(AddServiceChargeRequest request) {
		Employee employee = getEmployeeByUnionMemberId(request.unionMemberId);
		
		UnionMemberAffiliation unionMemberAffiliation = castUnionMemberAffiliation(employee.getAffiliation());
		unionMemberAffiliation.addServiceCharge(createServiceCharge(request));
	}


	private Employee getEmployeeByUnionMemberId(int unionMemberId) {
		return entityGateway.getEmployee(entityGateway.getEmployeeIdByUnionMemberId(unionMemberId));
	}

	private UnionMemberAffiliation castUnionMemberAffiliation(Affiliation affiliation) {
		if(affiliation instanceof UnionMemberAffiliation) {
			return (UnionMemberAffiliation) affiliation;
		} else {
			throw new TriedToAddSalesReceiptToNonCommissionedEmployeeException();
		}
	}

	private ServiceCharge createServiceCharge(AddServiceChargeRequest request) {
		return entityGateway.factory().serviceCharge(request.date, request.amount);
	}

}
