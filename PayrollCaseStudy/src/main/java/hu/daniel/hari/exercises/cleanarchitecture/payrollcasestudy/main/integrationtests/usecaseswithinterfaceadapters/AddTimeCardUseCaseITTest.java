package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;

public class AddTimeCardUseCaseITTest extends AbstractUseCaseITTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private LocalDate timeCardDate = A_DATE;
	private int workingHoursQty = 8;

	public AddTimeCardUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void testAddTimeCardUseCase() {
		givenAHourlyEmployee();
		whenAddingTimeCard();
		thenTimeCardShouldBeAdded(database.employeeGateway().findById(employeeId));
	}

	private void givenAHourlyEmployee() {
		useCaseFactory.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, "", "", 0));
	}

	private void whenAddingTimeCard() {
		useCaseFactory.addTimeCardUseCase().execute(new AddTimeCardRequest(employeeId, timeCardDate, workingHoursQty));
	}

	private void thenTimeCardShouldBeAdded(Employee employee) {
		TimeCard timeCard = TestUtils.singleResult(((HourlyPaymentClassification) employee.getPaymentClassification())
				.getTimeCardsIn(DateInterval.of(timeCardDate, timeCardDate)));
		assertEquals(workingHoursQty, timeCard.getWorkingHourQty());
	}
}
