package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayFunctionUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.PaymentMethodTypeResponse.PaymentMethodTypeResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class PayListUseCase extends EmployeeGatewayFunctionUseCase<PayListRequest, PayListResponse> {

	public PayListUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected PayListResponse executeInTransaction(PayListRequest request) {
		return new PayListResponseCreator().toResponse(createPayChecks(request.payDate));
	}

	private List<PayCheck> createPayChecks(LocalDate payDate) {
		return employeeGateway.findAll().stream()
			.filter(e -> e.isPayDate(payDate))
			.map(e -> e.createPayCheck(payDate))
			.collect(Collectors.toList());
	}

	private class PayListResponseCreator {
		private PaymentTypeResponseFactory paymentTypeResponseFactory = new PaymentTypeResponseFactory();
		private PaymentMethodTypeResponseFactory paymentMethodTypeResponseFactory = new PaymentMethodTypeResponseFactory();

		public PayListResponse toResponse(List<PayCheck> payChecks) {
			return new PayListResponse(payChecks.stream()
					.map(payCheck -> toResponse(payCheck))
					.collect(Collectors.toList())
					);
		}

		private PayListResponseItem toResponse(PayCheck payCheck) {
			Employee employee = employeeGateway.findById(payCheck.getEmployeeId());
			
			PayListResponseItem payListResponseItem = new PayListResponseItem();
			payListResponseItem.employeeId = payCheck.getEmployeeId();
			payListResponseItem.grossAmount = payCheck.getGrossAmount();
			payListResponseItem.deductionsAmount = payCheck.getDeductionsAmount();
			payListResponseItem.netAmount = payCheck.getNetAmount();
			payListResponseItem.name = employee.getName();
			payListResponseItem.paymentTypeResponse = employee.getPaymentType().accept(paymentTypeResponseFactory);
			payListResponseItem.paymentMethodTypeResponse = employee.getPaymentMethod().accept(paymentMethodTypeResponseFactory);
			return payListResponseItem;
		}

	}
}
