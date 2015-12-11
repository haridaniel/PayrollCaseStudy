package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;

public class AddSalesReceiptUseCase extends TransactionalDatabaseUseCase {

	private AddSalesReceiptRequestModel requestModel;

	public AddSalesReceiptUseCase(PayrollDatabase payrollDatabase, AddSalesReceiptRequestModel addSalesReceiptRequestModel) {
		super(payrollDatabase);
		this.requestModel = addSalesReceiptRequestModel;
	}

	@Override
	protected void executeInTransaction() {
		Employee employee = payrollDatabase.getEmployee(requestModel.employeeId);
		
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
		return payrollDatabase.factory().salesReceipt(requestModel.date, requestModel.amount);
	}
	
	public static class TriedToAddSalesReceiptToNonCommissionedEmployeeException extends RuntimeException {
	}
}
