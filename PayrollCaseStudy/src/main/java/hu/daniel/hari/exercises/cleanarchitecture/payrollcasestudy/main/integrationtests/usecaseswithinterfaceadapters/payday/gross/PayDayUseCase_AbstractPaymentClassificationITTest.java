package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday.gross;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday.AbstractPayDayUseCase_ITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;

public abstract class PayDayUseCase_AbstractPaymentClassificationITTest extends AbstractPayDayUseCase_ITTest {

	public PayDayUseCase_AbstractPaymentClassificationITTest(DatabaseProvider databaseProvider) {
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

	protected void thenPayCheckGrossAmountShouldBe(Collection<PayCheck> payChecks, int grossAmount) {
		assertThat(TestUtils.singleResult(payChecks).getGrossAmount(), is(grossAmount));
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
