package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecases.pay.list;

import java.time.LocalDate;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.AbstractMultipleDatabaseUseCaseTest;

public abstract class AbstractPayListUseCase_ITTest extends AbstractMultipleDatabaseUseCaseTest {

	public AbstractPayListUseCase_ITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	protected Collection<PayListResponseItem> whenGeneratePayUseCaseExecuted(LocalDate payDate) {
		return useCaseFactories.payListUseCase().execute(new PayListRequest(payDate))
				.payListResponseItems;
	}

}