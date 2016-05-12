package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate.gross;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate.AbstractPayListUseCase_ITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse.PayListResponseItem;

public abstract class PayListUseCase_AbstractPaymentTypeITTest extends AbstractPayListUseCase_ITTest {

	public PayListUseCase_AbstractPaymentTypeITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPayEmployee_OnAPayday_ShouldCreatePaycheck() throws Exception {
		givenAnEmployee();
		thenPayCheckShouldBeCreated(whenGeneratePayUseCaseExecuted(getAPayday()));
	}

	@Test
	public void testPayEmployee_OnNotAPayday_ShouldNotCreatePaycheck() throws Exception {
		givenAnEmployee();
		thenNoPayCheckShouldBeCreated(whenGeneratePayUseCaseExecuted(getNotAPayday()));
	}

	protected void thenPayCheckGrossAmountShouldBe(Collection<PayListResponseItem> payChecks, int grossAmount) {
		assertThat(TestUtils.singleResult(payChecks).grossAmount, is(grossAmount));
	}

	private void thenNoPayCheckShouldBeCreated(Collection<PayListResponseItem> payChecks) {
		assertThat(payChecks.isEmpty(), is(true));
	}

	private void thenPayCheckShouldBeCreated(Collection<PayListResponseItem> payChecks) {
		assertThat(payChecks.isEmpty(), is(false));
	}

	protected abstract void givenAnEmployee();
	protected abstract LocalDate getNotAPayday();
	protected abstract LocalDate getAPayday();
}
