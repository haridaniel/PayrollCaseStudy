package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;

public class AddTimeCardUseCaseTest extends AbstractUseCaseITTest {
	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);

	private int employeeId = 1;
	private String name = "Bob";
	private String address = "Liverside road";
	private int hourlyWage = 15;
	
	private LocalDate timeCardDate = A_DATE;
	private int workingHoursQty = 8;

	private AddHourlyEmployeeRequest addHourlyEmployeeRequest = new AddHourlyEmployeeRequest(employeeId, name, address, hourlyWage);
	private AddTimeCardRequest addTimeCardRequest = new AddTimeCardRequest(employeeId, timeCardDate, workingHoursQty);

	public AddTimeCardUseCaseTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void testAddTimeCardUseCase() {
		givenEmployee();
		whenAddingTimeCard();
		thenTimeCardShouldBeAdded(database.employeeGateway().findById(employeeId));
		//CLEAN THIS!
		//Add employee duplication
		//and others
	}

	private void givenEmployee() {
		useCaseFactory.addHourlyEmployeeUseCase().execute(addHourlyEmployeeRequest);
	}

	private void whenAddingTimeCard() {
		useCaseFactory.addTimeCardUseCase().execute(addTimeCardRequest);
	}

	private void thenTimeCardShouldBeAdded(Employee employee) {
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification())
				.getTimeCardsIn(DateInterval.of(timeCardDate, timeCardDate)));
		assertEquals(8, timeCard.getWorkingHourQty());
	}

	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}
}
