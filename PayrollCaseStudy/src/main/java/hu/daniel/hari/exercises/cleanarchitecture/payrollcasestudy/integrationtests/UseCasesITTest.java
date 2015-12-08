package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.UseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAEntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Test;

public class UseCasesITTest {

	private PayrollDatabase payrollDatabase = ITTestScope.DATABASE;

	@After
	public void clearDatabase() {
		EntityTransaction transaction = payrollDatabase.getTransaction();
		payrollDatabase.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testAddSalariedEmployeeTransaction() throws Exception {

		int employeeId = 1;
		int monthlySalary = 150000;
		UseCase payrollDatabaseUseCase = new AddSalariedEmployeeUseCase(payrollDatabase, employeeId, "Bob", "Home", monthlySalary);
		payrollDatabaseUseCase.execute();

		Employee employee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(employee);
		assertEquals(employee.getName(), "Bob");
		assertThat(employee.getPaymentMethod(), instanceOf(HoldPaymentMethod.class));
		
		assertThat(employee.getPaymentClassification(), instanceOf(SalariedPaymentClassification.class));
		assertThat(((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary(), is(monthlySalary));

		assertThat(employee.getPaymentSchedule(), instanceOf(MontlhyPaymentSchedule.class));
		
	}
	
	@Test
	public void testAddHourlyEmployeeTransaction() throws Exception {
		
		int employeeId = 1;
		int hourlyRate = 100;
		
		UseCase payrollDatabaseUseCase = new AddHourlyEmployeeUseCase(payrollDatabase, employeeId, "Bob", "Home", hourlyRate);
		payrollDatabaseUseCase.execute();
		
		Employee employee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(employee);
		assertEquals(employee.getName(), "Bob");
		assertThat(employee.getPaymentMethod(), instanceOf(HoldPaymentMethod.class));
		
		assertThat(employee.getPaymentClassification(), instanceOf(HourlyPaymentClassification.class));
		assertThat(((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage(), is(hourlyRate));
		assertThat(employee.getPaymentSchedule(), instanceOf(WeeklyPaymentSchedule.class));
		
	}

	@Test
	public void testDeleteEmployeeTransaction() throws Exception {
		payrollDatabase.addEmployee(testEmployee());
		
		assertNotNull(payrollDatabase.getEmployee(testEmployee().getId()));
		
		DeleteEmployeeUseCase deleteEmployeeUseCase = new DeleteEmployeeUseCase(payrollDatabase, testEmployee().getId());
		deleteEmployeeUseCase.execute();
		
		assertNull(payrollDatabase.getEmployee(testEmployee().getId()));
		
	}
	
	@Test
	public void testChangeEmployeeNameTransaction() throws Exception {
		new AddSalariedEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 1005)
			.execute();
		
		new ChangeEmployeeNameUseCase(payrollDatabase, testEmployee().getId(), "Janos")
			.execute();
		
		Employee employee = payrollDatabase.getEmployee(testEmployee().getId());
		assertEquals("Janos", employee.getName());
		
	}
	
	@Test
	public void testAddTimeCardTransaction() throws Exception {
		new AddHourlyEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 115)
			.execute();
		
		LocalDate date = LocalDate.of(2015, 11, 01);
		
		new AddTimeCardUseCase(payrollDatabase,
				new AddTimeCardRequestModel(testEmployee().getId(), date, 8))
				.execute();
		
		Employee employee = payrollDatabase.getEmployee(testEmployee().getId());
		assertNotNull(employee);
		assertThat(employee.getPaymentClassification(), instanceOf(HourlyPaymentClassification.class));
		
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCardsIn(DateInterval.of(date, date)));
		assertThat(timeCard, notNullValue());
		
		assertEquals(8, timeCard.getWorkingHourQty());
	}
	
	private Employee testEmployee() {
		Employee employee = payrollDatabase.factory().employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}

	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}
	
	
}
