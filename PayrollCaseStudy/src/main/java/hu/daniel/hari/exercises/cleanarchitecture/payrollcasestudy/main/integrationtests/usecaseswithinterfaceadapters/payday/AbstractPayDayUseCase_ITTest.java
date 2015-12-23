package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.PaydayRequest;

public abstract class AbstractPayDayUseCase_ITTest extends AbstractUseCaseITTest {

	public AbstractPayDayUseCase_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPayEmployee_OnAPayday_ShouldCreatePaycheck() throws Exception {
		givenAnEmployee();
		thenPayCheckShouldBeCreated(whenPayDayUseCaseExecuted(getAPayday()));
	}

	@Test
	public void testPayEmployee_OnNotAPayday_ShouldNotCreatePaycheck() throws Exception {
		givenAnEmployee();
		thenNoPayCheckShouldBeCreated(whenPayDayUseCaseExecuted(getNotAPayday()));
	}

	protected Collection<PayCheck> whenPayDayUseCaseExecuted(LocalDate payDate) {
		PaydayUseCase paydayUseCase = useCaseFactory.paydayUseCase();
		paydayUseCase.execute(new PaydayRequest(payDate));
		return paydayUseCase.getPayChecks();
	}

	private void thenNoPayCheckShouldBeCreated(Collection<PayCheck> payChecks) {
		assertThat(payChecks.isEmpty(), is(true));
	}

	private void thenPayCheckShouldBeCreated(Collection<PayCheck> payChecks) {
		assertThat(payChecks.isEmpty(), is(false));
	}

	protected abstract void givenAnEmployee();
	protected abstract LocalDate getNotAPayday();
	protected abstract LocalDate getAPayday();
}
