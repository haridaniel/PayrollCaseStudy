package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.testutil.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.AbstractMultipleDatabaseUseCaseTest;

public class AddSalesReceiptUseCaseITTest extends AbstractMultipleDatabaseUseCaseTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private LocalDate salesReceiptDate = A_DATE;
	private int salesReceiptAmount = 25000;

	public AddSalesReceiptUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}
	
	@Test
	public void testAddSalesReceiptUseCase() {
		givenACommissionedEmployee();
		whenAddingSalesReceipt();
		thenSalesReceiptShouldBeAdded(database.employeeGateway().findById(employeeId));
	}

	private void givenACommissionedEmployee() {
		useCaseFactories.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(employeeId, "", "", 0, 0));
	}

	private void whenAddingSalesReceipt() {
		useCaseFactories.addSalesReceiptUseCaseFactory().execute(new AddSalesReceiptRequest(employeeId, salesReceiptDate, salesReceiptAmount));
	}

	private void thenSalesReceiptShouldBeAdded(Employee employee) {
		SalesReceipt salesReceipt = TestUtils.singleResult(((CommissionedPaymentType) employee.getPaymentType())
				.getSalesReceiptsIn(DateInterval.ofSingleDate(salesReceiptDate)));
		assertThat(salesReceipt.getAmount(), is(salesReceiptAmount));
	}
}
