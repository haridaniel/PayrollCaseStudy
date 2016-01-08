package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate;

import java.time.LocalDate;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate.GeneratePayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GeneratePayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GeneratePayResponse.PayCheckResponse;

public abstract class AbstractGeneratePayUseCase_ITTest extends AbstractUseCaseITTest {

	public AbstractGeneratePayUseCase_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	protected Collection<PayCheckResponse> whenGeneratePayUseCaseExecuted(LocalDate payDate) {
		GeneratePayUseCase generatePayUseCase = useCaseFactory.generatePayUseCase();
		generatePayUseCase.execute(new GeneratePayRequest(payDate));
		return generatePayUseCase.getResponse().payCheckResponses;
	}

}