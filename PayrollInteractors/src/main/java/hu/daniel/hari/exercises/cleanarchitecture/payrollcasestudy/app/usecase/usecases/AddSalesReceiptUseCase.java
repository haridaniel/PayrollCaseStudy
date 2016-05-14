package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt.SalesReceiptFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.newversion.usecases.EmployeeGatewayCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddSalesReceiptUseCase extends EmployeeGatewayCommandUseCase<AddSalesReceiptRequest> {

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
		
		castCommissionedPaymentType(employee.getPaymentType())
			.addSalesReceipt(createSalesReceipt(request));
	}

	private CommissionedPaymentType castCommissionedPaymentType(PaymentType paymentType) {
		if(paymentType instanceof CommissionedPaymentType) {
			return (CommissionedPaymentType) paymentType;
		} else {
			throw new TriedToAddSalesReceiptToNonCommissionedEmployeeException();
		}
	}
	
	private SalesReceipt createSalesReceipt(AddSalesReceiptRequest request) {
		return salesReceiptFactory.salesReceipt(request.date, request.amount);
	}
	
	public static class TriedToAddSalesReceiptToNonCommissionedEmployeeException extends RuntimeException {
	}
	
}
