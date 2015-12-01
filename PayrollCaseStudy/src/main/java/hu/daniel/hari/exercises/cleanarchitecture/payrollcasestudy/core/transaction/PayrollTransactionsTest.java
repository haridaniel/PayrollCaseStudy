package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
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

import org.junit.After;
import org.junit.Test;

public class PayrollTransactionsTest {

	private PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();
//	private PayrollDatabase payrollDatabase = new JPAPayrollDatabaseModule().getPayrollDatabase();

	private static Employee testEmployee() {
		return createTestEmployee(1);
	}
	
	@After
	public void clearDatabase() {
		payrollDatabase.clearEmployees();
	}

	private static Employee createTestEmployee(int employeeId) {
		Employee employee = new Employee();
		employee.id = employeeId;
		employee.setName("Boob");
		return employee;
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
		assertThat(employee.paymentMethod, instanceOf(HoldPaymentMethod.class));
		
		assertThat(employee.getPaymentClassification(), instanceOf(SalariedPaymentClassification.class));
		assertThat(((SalariedPaymentClassification) employee.getPaymentClassification()).monthlySalary, is(monthlySalary));
		assertThat(employee.paymentSchedule, instanceOf(MontlhyPaymentSchedule.class));
		
	}
	
	@Test
	public void testAddHourlyRatedEmployeeTransaction() throws Exception {
		
		int employeeId = 1;
		int hourlyRate = 100;
		
		Transaction payrollDatabaseTransaction = new AddHourlyEmployeeTransaction(payrollDatabase, employeeId, "Bob", "Home", hourlyRate);
		payrollDatabaseTransaction.execute();
		
		Employee employee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(employee);
		assertEquals(employee.getName(), "Bob");
		assertThat(employee.paymentMethod, instanceOf(HoldPaymentMethod.class));
		
		assertThat(employee.getPaymentClassification(), instanceOf(HourlyPaymentClassification.class));
		assertThat(((HourlyPaymentClassification) employee.getPaymentClassification()).hourlyRate, is(hourlyRate));
		assertThat(employee.paymentSchedule, instanceOf(WeeklyPaymentSchedule.class));
		
	}

	@Test
	public void testDeleteEmployeeTransaction() throws Exception {
		payrollDatabase.addEmployee(testEmployee());
		
		assertNotNull(payrollDatabase.getEmployee(testEmployee().id));
		
		DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(payrollDatabase, testEmployee().id);
		deleteEmployeeTransaction.execute();
		
		assertNull(payrollDatabase.getEmployee(testEmployee().id));
		
	}
	
	@Test
	public void testAddTimeCardTransaction() throws Exception {
		new AddHourlyEmployeeTransaction(payrollDatabase, testEmployee().id, testEmployee().getName(), testEmployee().address, 115)
			.execute();
		
		LocalDate date = LocalDate.of(2015, 11, 01);
		
		new AddTimeCardTransaction(payrollDatabase,
				new AddTimeCardRequestModel(testEmployee().id, date, 8))
				.execute();
		
		Employee employee = payrollDatabase.getEmployee(testEmployee().id);
		assertNotNull(employee);
		assertEquals(HourlyPaymentClassification.class, employee.getPaymentClassification().getClass());
		TimeCard timeCard = ((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCard(date);
		assertNotNull(timeCard);
		
		assertEquals(8, timeCard.workingHourQty);
	}

	@Test
	public void testChangeEmployeeNameTransaction() throws Exception {
		new AddSalariedEmployeeTransaction(payrollDatabase, testEmployee().id, testEmployee().getName(), testEmployee().address, 1005)
			.execute();
		
		new ChangeEmployeeNameTransaction(payrollDatabase, testEmployee().id, "Janos")
			.execute();
		
		Employee employee = payrollDatabase.getEmployee(testEmployee().id);
		assertEquals("Janos", employee.getName());
		
	}
	
}
