package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;

public class AddSalesReceiptUseCase extends TransactionalUseCase<AddSalesReceiptRequest> {

	public AddSalesReceiptUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(AddSalesReceiptRequest request) {
		Employee employee = entityGateway.getEmployee(request.employeeId);
		
		castCommissionedPaymentClassification(employee.getPaymentClassification())
			.addSalesReceipt(createSalesReceipt(request));
	}

	private CommissionedPaymentClassification castCommissionedPaymentClassification(PaymentClassification paymentClassification) {
		if(paymentClassification instanceof CommissionedPaymentClassification) {
			return (CommissionedPaymentClassification) paymentClassification;
		} else {
			throw new TriedToAddSalesReceiptToNonCommissionedEmployeeException();
		}
	}
	
	private SalesReceipt createSalesReceipt(AddSalesReceiptRequest request) {
		return entityGateway.factory().salesReceipt(request.date, request.amount);
	}
	
	public static class TriedToAddSalesReceiptToNonCommissionedEmployeeException extends RuntimeException {
	}
}
