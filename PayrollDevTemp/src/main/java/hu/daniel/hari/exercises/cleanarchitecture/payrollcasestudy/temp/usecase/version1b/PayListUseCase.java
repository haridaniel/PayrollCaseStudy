package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class PayListUseCase extends TransactionalFunctionUseCase<PayListRequest, PayListResponse> {

	public PayListUseCase(TransactionalRunner transactionalRunner) {
		super(transactionalRunner);
	}

	@Override
	protected PayListResponse executeInTransaction(PayListRequest request) {
		System.out.println("get paylist");
		return null;
	}


}
