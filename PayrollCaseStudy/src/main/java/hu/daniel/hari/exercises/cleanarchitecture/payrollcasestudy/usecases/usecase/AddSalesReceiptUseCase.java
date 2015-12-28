package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt.SalesReceiptFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.AddSalesReceiptRequest;

public class AddSalesReceiptUseCase extends TransactionalEmployeeUseCase<AddSalesReceiptRequest> {

	private SalesReceiptFactory salesReceiptFactory;

	public AddSalesReceiptUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			SalesReceiptFactory salesReceiptFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.salesReceiptFactory = salesReceiptFactory;
	}

	@Override
	protected void executeInTransaction(AddSalesReceiptRequest request) {
		Employee employee = employeeGateway.findById(request.employeeId);
		
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
		return salesReceiptFactory.salesReceipt(request.date, request.amount);
	}
	
	public static class TriedToAddSalesReceiptToNonCommissionedEmployeeException extends RuntimeException {
	}
	
	public static interface AddSalesReceiptUseCaseFactory {
		AddSalesReceiptUseCase addSalesReceiptUseCaseFactory();
	}
	
}
