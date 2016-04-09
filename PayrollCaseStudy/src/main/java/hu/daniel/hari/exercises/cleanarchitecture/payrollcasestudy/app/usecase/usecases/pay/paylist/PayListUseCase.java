package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentMethodTypeResponse.PaymentMethodVisitorTypeResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeVisitorResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class PayListUseCase extends TransactionalEmployeeGatewayUseCase<PayListRequest> implements HasResponse<PayListResponse> {

	private PayListResponse response;

	public PayListUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(PayListRequest request) {
		List<PayCheck> payChecks = createPayChecks(request.payDate);
		response = new PayListResponseCreator().toResponse(payChecks);
	}

	private List<PayCheck> createPayChecks(LocalDate payDate) {
		List<PayCheck> payChecks = new ArrayList<>();
		for (Employee employee : employeeGateway.findAll()) {
			if(employee.isPayDate(payDate)) {
				payChecks.add(employee.createPayCheck(payDate));
			}
		}
		return payChecks;
	}

	@Override
	public PayListResponse getResponse() {
		return response;
	}

	private class PayListResponseCreator {
		private PaymentTypeVisitorResponseFactory paymentTypeVisitorResponseFactory = new PaymentTypeVisitorResponseFactory();
		private PaymentMethodVisitorTypeResponseFactory paymentMethodVisitorTypeResponseFactory = new PaymentMethodVisitorTypeResponseFactory();

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
			payListResponseItem.paymentTypeResponse = employee.getPaymentType().accept(paymentTypeVisitorResponseFactory);
			payListResponseItem.paymentMethodTypeResponse = employee.getPaymentMethod().accept(paymentMethodVisitorTypeResponseFactory);
			return payListResponseItem;
		}

	}

	public static interface PayListUseCaseFactory {
		PayListUseCase payListUseCase();
	}
}
