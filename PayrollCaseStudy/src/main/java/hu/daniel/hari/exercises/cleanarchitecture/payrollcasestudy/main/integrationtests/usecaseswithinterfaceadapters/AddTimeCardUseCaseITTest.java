package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.junit.Assert.*;
import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.AddTimeCardUseCase.TimeCardAlreadyExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.AddTimeCardRequest;

public class AddTimeCardUseCaseITTest extends AbstractUseCaseITTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private LocalDate timeCardDate = A_DATE;
	private int workingHoursQty = 8;

	public AddTimeCardUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void testAddTimeCard() {
		givenAHourlyEmployee();
		whenAddingTimeCard();
		thenTimeCardShouldBeAdded(database.employeeGateway().findById(employeeId));
	}

	@Test(expected=TimeCardAlreadyExistsException.class)
	public void shouldThrowExceptionIfTimeCardAlreadyExists() {
		givenAHourlyEmployee();
		whenAddingTimeCardTwoTimes();
	}
	
	private void givenAHourlyEmployee() {
		useCaseFactories.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, "", "", 0));
	}

	private void whenAddingTimeCard() {
		useCaseFactories.addTimeCardUseCase().execute(new AddTimeCardRequest(employeeId, timeCardDate, workingHoursQty));
	}

	private void whenAddingTimeCardTwoTimes() {
		whenAddingTimeCard();
		whenAddingTimeCard();
	}

	private void thenTimeCardShouldBeAdded(Employee employee) {
		TimeCard timeCard = ((HourlyPaymentType) employee.getPaymentType())
				.getTimeCard(timeCardDate).get();
		assertEquals(workingHoursQty, timeCard.getWorkingHourQty());
	}
}
