package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddHourlyEmployeeRequest;

public class AddSalesReceiptUseCaseITTest extends AbstractUseCaseITTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private LocalDate salesReceiptDate = A_DATE;
	private int salesReceiptAmount = 25000;

	public AddSalesReceiptUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void testAddSalesReceiptUseCase() {
		givenACommissionedEmployee();
		whenAddingSalesReceipt();
		thenSalesReceiptShouldBeAdded(database.employeeGateway().findById(employeeId));
	}

	private void givenACommissionedEmployee() {
		useCaseFactory.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(employeeId, "", "", 0, 0));
	}

	private void whenAddingSalesReceipt() {
		useCaseFactory.addSalesReceiptUseCaseFactory().execute(new AddSalesReceiptRequest(employeeId, salesReceiptDate, salesReceiptAmount));
	}

	private void thenSalesReceiptShouldBeAdded(Employee employee) {
		SalesReceipt salesReceipt = TestUtils.singleResult(((CommissionedPaymentClassification) employee.getPaymentClassification())
				.getSalesReceiptsIn(DateInterval.ofSingleDate(salesReceiptDate)));
		assertThat(salesReceipt.getAmount(), is(salesReceiptAmount));
	}
}
