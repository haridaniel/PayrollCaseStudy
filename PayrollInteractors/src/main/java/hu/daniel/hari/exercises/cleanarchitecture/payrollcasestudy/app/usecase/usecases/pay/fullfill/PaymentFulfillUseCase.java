package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AbstractUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.fullfillers.PaymentFulfillerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PaymentFulfillRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PaymentFulfillResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.FunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class PaymentFulfillUseCase extends AbstractUseCase implements FunctionUseCase<PaymentFulfillRequest, PaymentFulfillResponse> {

	private EmployeeGateway employeeGateway;
	private PaymentFulfillerFactory paymentFulfillerFactory;

	public PaymentFulfillUseCase(
			EmployeeGateway employeeGateway,
			TransactionalRunner transactionalRunner,
			BankTransferPort bankTransferPort
			) {
		super();
		this.employeeGateway = employeeGateway;
		paymentFulfillerFactory = new PaymentFulfillerFactory(bankTransferPort, transactionalRunner);
	}
	
	@Override
	public PaymentFulfillResponse execute(PaymentFulfillRequest request) {
		checkFirstExecution();
		return fullfill(createPayChecks(request.payDate));
	}

	private List<PayCheck> createPayChecks(LocalDate payDate) {
		return employeeGateway.findAll().stream()
			.filter(e -> e.isPayDate(payDate))
			.map(e -> e.createPayCheck(payDate))
			.collect(Collectors.toList());
	}

	private PaymentFulfillResponse fullfill(List<PayCheck> payChecks) {
		payChecks.stream()
			.forEach(it -> fullfill(it));
		return new PaymentFulfillResponse(payChecks.size(), calcTotalNetAmount(payChecks));
	}
	
	private int calcTotalNetAmount(List<PayCheck> payChecks) {
		return payChecks.stream().mapToInt(PayCheck::getNetAmount).sum();
	}

	private void fullfill(PayCheck payCheck) {
		getEmployee(payCheck.getEmployeeId()).getPaymentMethod().accept(paymentFulfillerFactory).fulfillPayment(payCheck);
	}

	private Employee getEmployee(int employeeId) {
		return employeeGateway.findById(employeeId);
	}

}
