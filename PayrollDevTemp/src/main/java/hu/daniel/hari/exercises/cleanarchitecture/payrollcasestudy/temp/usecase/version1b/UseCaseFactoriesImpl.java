package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.Response.EmptyResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class UseCaseFactoriesImpl implements UseCaseFactories {

	private TransactionalRunner transactionalRunner = new FakeTransactionalRunner();

	@Override
	public FunctionUseCase<PayListRequest, PayListResponse> payListUseCase() {
		return new PayListUseCase(transactionalRunner);
	}

	@Override
	public CommandUseCase<DeleteEmployeeRequest> deleteEmployeeUseCase() {
		return new DeleteEmployeeUseCase(transactionalRunner);
	}
	

	

}
