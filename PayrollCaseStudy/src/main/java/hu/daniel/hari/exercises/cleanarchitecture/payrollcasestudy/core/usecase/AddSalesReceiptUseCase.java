package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;

public class AddSalesReceiptUseCase extends TransactionalUseCase {

	private AddSalesReceiptRequestModel requestModel;

	public AddSalesReceiptUseCase(Database database, AddSalesReceiptRequestModel addSalesReceiptRequestModel) {
		super(database);
		this.requestModel = addSalesReceiptRequestModel;
	}

	@Override
	protected void executeInTransaction() {
		Employee employee = entityGateway.getEmployee(requestModel.employeeId);
		
		castCommissionedPaymentClassification(employee.getPaymentClassification())
			.addSalesReceipt(createSalesReceipt());
	}

	private CommissionedPaymentClassification castCommissionedPaymentClassification(PaymentClassification paymentClassification) {
		if(paymentClassification instanceof CommissionedPaymentClassification) {
			return (CommissionedPaymentClassification) paymentClassification;
		} else {
			throw new TriedToAddSalesReceiptToNonCommissionedEmployeeException();
		}
	}
	
	private SalesReceipt createSalesReceipt() {
		return entityGateway.factory().salesReceipt(requestModel.date, requestModel.amount);
	}
	
	public static class TriedToAddSalesReceiptToNonCommissionedEmployeeException extends RuntimeException {
	}
}
