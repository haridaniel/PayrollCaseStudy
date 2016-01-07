package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt.SalesReceiptFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddSalesReceiptUseCase extends TransactionalUseCase<AddSalesReceiptRequest> {

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
