package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

import java.time.LocalDate;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

public class PayrollTransactionsTest {

	private PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();
//	private PayrollDatabase payrollDatabase = new JPAPayrollDatabaseModule().getPayrollDatabase();

	@After
	public void clearDatabase() {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testAddSalariedEmployeeTransaction() throws Exception {

		int employeeId = 1;
		int monthlySalary = 150000;
		Transaction payrollDatabaseTransaction = new AddSalariedEmployeeTransaction(payrollDatabase, employeeId, "Bob", "Home", monthlySalary);
		payrollDatabaseTransaction.execute();

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
		
		Transaction payrollDatabaseTransaction = new AddHourlyEmployeeTransaction(payrollDatabase, employeeId, "Bob", "Home", hourlyRate);
		payrollDatabaseTransaction.execute();
		
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
		
		DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(payrollDatabase, testEmployee().getId());
		deleteEmployeeTransaction.execute();
		
		assertNull(payrollDatabase.getEmployee(testEmployee().getId()));
		
	}
	
	@Test
	public void testChangeEmployeeNameTransaction() throws Exception {
		new AddSalariedEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 1005)
			.execute();
		
		new ChangeEmployeeNameTransaction(payrollDatabase, testEmployee().getId(), "Janos")
			.execute();
		
		Employee employee = payrollDatabase.getEmployee(testEmployee().getId());
		assertEquals("Janos", employee.getName());
		
	}

	@Test
	public void testAddTimeCardTransaction() throws Exception {
		new AddHourlyEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 115)
			.execute();
		
		LocalDate date = LocalDate.of(2015, 11, 01);
		
		new AddTimeCardTransaction(payrollDatabase,
				new AddTimeCardRequestModel(testEmployee().getId(), date, 8))
				.execute();
		
		Employee employee = payrollDatabase.getEmployee(testEmployee().getId());
		assertNotNull(employee);
		assertThat(employee.getPaymentClassification(), instanceOf(HourlyPaymentClassification.class));
		TimeCard timeCard = ((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCard(date);
		assertNotNull(timeCard);
		
		assertEquals(8, timeCard.workingHourQty);
	}

	private static Employee testEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}
	
	
}
