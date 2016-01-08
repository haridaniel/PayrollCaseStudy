package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GeneratePayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GeneratePayResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GeneratePayResponse.PayCheckResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GeneratePayUseCase extends TransactionalEmployeeGatewayUseCase<GeneratePayRequest> implements HasResponse<GeneratePayResponse> {

	private GeneratePayResponse response;

	public GeneratePayUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GeneratePayRequest request) {
		List<PayCheck> payChecks = generate(request);
		response = new GeneratePayResponseCreator().toResponse(payChecks);
	}

	private List<PayCheck> generate(GeneratePayRequest request) {
		List<PayCheck> payChecks = new ArrayList<>();
		for (Employee employee : employeeGateway.findAll()) {
			if(employee.isPayDate(request.payDate)) {
				payChecks.add(employee.createPayCheck(request.payDate));
			}
		}
		return payChecks;
	}

	@Override
	public GeneratePayResponse getResponse() {
		return response;
	}

	private static class GeneratePayResponseCreator {

		public GeneratePayResponse toResponse(List<PayCheck> payChecks) {
			return new GeneratePayResponse(payChecks.stream()
					.map(payCheck -> toResponse(payCheck))
					.collect(Collectors.toList())
					);
		}

		private PayCheckResponse toResponse(PayCheck payCheck) {
			PayCheckResponse payCheckResponse = new PayCheckResponse();
			payCheckResponse.employeeId = payCheck.getEmployeeId();
			payCheckResponse.name = "?";
			payCheckResponse.grossAmount = payCheck.getGrossAmount();
			payCheckResponse.deductionsAmount = payCheck.getDeductionsAmount();
			payCheckResponse.netAmount = payCheck.getNetAmount();
			return payCheckResponse;
		}

	}

	public static interface GeneratePayUseCaseFactory {
		GeneratePayUseCase generatePayUseCase();
	}
}
