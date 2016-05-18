package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.other.timecard.UpdateTimeCardUseCase.TimeCardNotExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.timecard.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.timecard.UpdateTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.AbstractMultipleDatabaseUseCaseTest;

public class UpdateTimeCardUseCaseITTest extends AbstractMultipleDatabaseUseCaseTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private LocalDate timeCardDate = A_DATE;
	private int workingHoursQty = 8;
	private int updatedWorkingHoursQty = 80;

	public UpdateTimeCardUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}
	
	@Test
	public void testUpdateTime() {
		givenAHourlyEmployee();
		givenATimeCard();
		whenUpdatingTimeCard();
		thenTimeCardShouldBeUpdated(database.employeeGateway().findById(employeeId));
	}
	
	@Test(expected=TimeCardNotExistsException.class)
	public void shouldThrowExceptionIfTimeCardNotExists() {
		givenAHourlyEmployee();
		whenUpdatingTimeCard();
	}
	
	private void givenAHourlyEmployee() {
		useCaseFactories.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, "", "", 0));
	}

	private void givenATimeCard() {
		useCaseFactories.addTimeCardUseCase().execute(new AddTimeCardRequest(employeeId, timeCardDate, workingHoursQty));
	}
	
	private void whenUpdatingTimeCard() {
		useCaseFactories.updateTimeCardUseCase().execute(new UpdateTimeCardRequest(employeeId, timeCardDate, updatedWorkingHoursQty));
	}

	private void thenTimeCardShouldBeUpdated(Employee employee) {
		TimeCard timeCard = ((HourlyPaymentType) employee.getPaymentType())
				.getTimeCard(timeCardDate).get();
		assertEquals(updatedWorkingHoursQty, timeCard.getWorkingHourQty());
	}
}
