package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday;

import java.time.LocalDate;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.PaydayRequest;

public abstract class AbstractPayDayUseCase_ITTest extends AbstractUseCaseITTest {

	public AbstractPayDayUseCase_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	protected Collection<PayCheck> whenPayDayUseCaseExecuted(LocalDate payDate) {
		PaydayUseCase paydayUseCase = useCaseFactory.paydayUseCase();
		paydayUseCase.execute(new PaydayRequest(payDate));
		return paydayUseCase.getPayChecks();
	}

}