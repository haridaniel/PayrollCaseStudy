package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.fulfiller.PaymentFulFiller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.fulfiller.PaymentFulFillerSelector;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GeneratePayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GeneratePayResponse;
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
		List<PayCheck> payChecks = new ArrayList<>();
		for (Employee employee : employeeGateway.findAll()) {
			if(employee.isPayDate(request.payDate)) {
				payChecks.add(employee.createPayCheck(request.payDate));
			}
		}
		response = new GeneratePayResponse(payChecks);
	}
	
	@Override
	public GeneratePayResponse getResponse() {
		return response;
	}

	public static interface PaydayUseCaseFactory {
		GeneratePayUseCase generatePayUseCase();
	}
	
}
