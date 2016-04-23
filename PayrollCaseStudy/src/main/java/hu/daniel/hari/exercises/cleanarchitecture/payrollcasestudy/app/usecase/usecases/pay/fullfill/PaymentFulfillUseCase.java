package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.UseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.fullfillers.PaymentFulfillerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.PaymentFulfillRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.PaymentFulfillResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;

public class PaymentFulfillUseCase implements UseCase<PaymentFulfillRequest>, HasResponse<PaymentFulfillResponse> {

	private EmployeeGateway employeeGateway;
	private PaymentFulfillerFactory paymentFulfillerFactory;
	private PaymentFulfillResponse response;

	public PaymentFulfillUseCase(
			EmployeeGateway employeeGateway,
			PaymentFulfillerFactory paymentFulfillerFactory
			) {
		super();
		this.employeeGateway = employeeGateway;
		this.paymentFulfillerFactory = paymentFulfillerFactory;
	}
	
	@Override
	public void execute(PaymentFulfillRequest request) {
		response = fullfill(createPayChecks(request.payDate));
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
		return new PaymentFulfillResponse(payChecks.size(), calcTotalGrossAmount(payChecks));
	}
	
	private int calcTotalGrossAmount(List<PayCheck> payChecks) {
		return payChecks.stream().mapToInt(PayCheck::getGrossAmount).sum();
	}

	private void fullfill(PayCheck payCheck) {
		getEmployee(payCheck.getEmployeeId()).getPaymentMethod().accept(paymentFulfillerFactory).fulfillPayment(payCheck);
	}

	private Employee getEmployee(int employeeId) {
		return employeeGateway.findById(employeeId);
	}

	public static interface PaymentFulfillUseCaseFactory {
		PaymentFulfillUseCase paymentFulfillUseCase();
	}

	@Override
	public PaymentFulfillResponse getResponse() {
		return response;
	}


	
}
