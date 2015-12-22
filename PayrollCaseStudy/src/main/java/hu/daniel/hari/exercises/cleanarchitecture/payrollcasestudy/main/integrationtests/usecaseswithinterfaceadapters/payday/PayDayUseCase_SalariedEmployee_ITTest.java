package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.PaydayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;

public class PayDayUseCase_SalariedEmployee_ITTest extends AbstractUseCaseITTest {
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);

	private final int employeeId = 1;
	private final int monthlySalary = 1000;
	private LocalDate payDate;

	public PayDayUseCase_SalariedEmployee_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void paySingleSalariedEmployee_OnNotPayday_ShouldNotCreatePayCheck() throws Exception {
		givenASalariedEmployee();
		givenPayDateNotAPayday();
		thenNoPayCheckShouldBeCreated(whenPayDayUseCaseExecuted());
	}

	@Test
	public void paySingleSalariedEmployee_OnPayday_ShouldCreatePayCheck() throws Exception {
		givenASalariedEmployee();
		givenPayDateLastDayOfAMonth();
		thenPayCheckNetAmountShouldBeTheSalary(whenPayDayUseCaseExecuted());
	}

	private void givenASalariedEmployee() {
		useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", monthlySalary));
	}

	private void givenPayDateNotAPayday() {
		payDate = LAST_DAY_OF_A_MONTH.minusDays(5);
	}

	private void givenPayDateLastDayOfAMonth() {
		payDate = LAST_DAY_OF_A_MONTH;
	}

	private Collection<PayCheck> whenPayDayUseCaseExecuted() {
		PaydayUseCase paydayUseCase = useCaseFactory.paydayUseCase();
		paydayUseCase.execute(new PaydayRequest(payDate));
		return paydayUseCase.getPayChecks();
	}

	private void thenNoPayCheckShouldBeCreated(Collection<PayCheck> payChecks) {
		assertTrue(payChecks.isEmpty());
	}

	private void thenPayCheckNetAmountShouldBeTheSalary(Collection<PayCheck> payChecks) {
		assertThat(TestUtils.singleResult(payChecks).getNetAmount(), is(monthlySalary));
	}

}
