package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.pay.list;

import java.time.LocalDate;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.AbstractMultipleDatabaseUseCaseTest;

public abstract class AbstractPayListUseCase_ITTest extends AbstractMultipleDatabaseUseCaseTest {

	public AbstractPayListUseCase_ITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	protected Collection<PayListResponseItem> whenGeneratePayUseCaseExecuted(LocalDate payDate) {
		return useCaseFactories.payListUseCase().execute(new PayListRequest(payDate))
				.payListResponseItems;
	}

}