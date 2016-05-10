package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate.gross;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.PayListResponse.PayListResponseItem;

public class PayListUseCase_SalariedEmployee_ITTest extends PayListUseCase_AbstractPaymentTypeITTest {
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	
	private static final LocalDate A_PAYDAY = LAST_DAY_OF_A_MONTH;
	private static final LocalDate NOT_A_PAYDAY = LAST_DAY_OF_A_MONTH.minusDays(5);

	private final int employeeId = 1;
	private final int monthlySalary = 1000;

	public PayListUseCase_SalariedEmployee_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPaySingleSalariedEmployee_OnPayday_ShouldCreateCorrectPayCheck() throws Exception {
		givenASalariedEmployee();
		thenPayCheckGrossAmountSumShouldBeTheSalary(whenGeneratePayUseCaseExecuted(getAPayday()));
	}

	private void givenASalariedEmployee() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", monthlySalary));
	}

	private void thenPayCheckGrossAmountSumShouldBeTheSalary(Collection<PayListResponseItem> payChecks) {
		thenPayCheckGrossAmountShouldBe(payChecks, monthlySalary);
	}

	@Override
	protected void givenAnEmployee() {
		givenASalariedEmployee();
	}

	@Override
	protected LocalDate getNotAPayday() {
		return NOT_A_PAYDAY;
	}

	@Override
	protected LocalDate getAPayday() {
		return A_PAYDAY;
	}

}
