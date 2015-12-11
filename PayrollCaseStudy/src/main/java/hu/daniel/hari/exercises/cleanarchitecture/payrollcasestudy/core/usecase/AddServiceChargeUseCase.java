package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddServiceChargeRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase.TriedToAddSalesReceiptToNonCommissionedEmployeeException;

public class AddServiceChargeUseCase extends TransactionalDatabaseUseCase {

	private AddServiceChargeRequestModel requestModel;

	public AddServiceChargeUseCase(PayrollDatabase payrollDatabase, AddServiceChargeRequestModel addServiceChargeRequestModel) {
		super(payrollDatabase);
		this.requestModel = addServiceChargeRequestModel;
	}

	@Override
	protected void executeInTransaction() {
		Employee employee = getEmployeeByUnionMemberId();
		
		UnionMemberAffiliation unionMemberAffiliation = castUnionMemberAffiliation(employee.getAffiliation());
		unionMemberAffiliation.addServiceCharge(createServiceCharge());
	}


	private Employee getEmployeeByUnionMemberId() {
		return payrollDatabase.getEmployee(payrollDatabase.getEmployeeIdByUnionMemberId(requestModel.unionMemberId));
	}

	private UnionMemberAffiliation castUnionMemberAffiliation(Affiliation affiliation) {
		if(affiliation instanceof UnionMemberAffiliation) {
			return (UnionMemberAffiliation) affiliation;
		} else {
			throw new TriedToAddSalesReceiptToNonCommissionedEmployeeException();
		}
	}

	private ServiceCharge createServiceCharge() {
		return payrollDatabase.factory().serviceCharge(requestModel.date, requestModel.amount);
	}

}
