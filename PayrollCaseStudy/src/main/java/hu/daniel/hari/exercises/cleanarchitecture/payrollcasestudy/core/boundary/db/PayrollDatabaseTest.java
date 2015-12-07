package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import org.junit.After;
import org.junit.Test;

public abstract class PayrollDatabaseTest {
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);

	private PayrollDatabase database;

	public PayrollDatabaseTest(PayrollDatabase payrollDatabase) {
		this.database = payrollDatabase;
	}

	private Employee testEmployee() {
		Employee employee = database.factory().employee();
		employee.setId(1);
		employee.setName("Bob");
		employee.setPaymentMethod(database.factory().holdPaymentMethod());
		return employee;
	}

	private Employee testEmployee2() {
		Employee employee = database.factory().employee();
		employee.setId(2);
		employee.setName("Robert");
		return employee;
	}

	@Test
	public void testTransaction() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		database.addEmployee(testEmployee());
		database.addEmployee(testEmployee2());
		transaction.commit();
	}
	
	@Test
	public void testTransactionRollback() throws Exception {
		
		
		
	}

	@After
	public void clearDatabase() {
		EntityTransaction transaction = database.getTransaction();
		database.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testAddEmployee() {
		int employeeId = testEmployee().getId();
		Employee employee = testEmployee();
		database.addEmployee(employee);

		Employee returnedEmployee = database.getEmployee(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.getName(), returnedEmployee.getName());

	}

	@Test
	public void testAddSalariedAndHourlyEmployee() throws Exception {
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentClassification(database.factory().salariedPaymentClassification(5566));
		database.addEmployee(testEmployee);

		Employee testEmployee2 = testEmployee2();
		testEmployee2.setPaymentClassification(database.factory().hourlyPaymentClassification(13));
		database.addEmployee(testEmployee2);

		{
			Employee employee = database.getEmployee(testEmployee.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((SalariedPaymentClassification) paymentClassification).getMonthlySalary(), 5566);
		}

		{
			Employee employee = database.getEmployee(testEmployee2.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((HourlyPaymentClassification) paymentClassification).getHourlyWage(), 13);
		}

	}

	@Test
	public void testChangeMonthlySalary() throws Exception {
		// GIVEN
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentClassification(database.factory().salariedPaymentClassification(5566));
		database.addEmployee(testEmployee);

		// WHEN
		{
			Employee employee = database.getEmployee(testEmployee.getId());
			((SalariedPaymentClassification) employee.getPaymentClassification()).setMonthlySalary(4000);
		}

		// THEN
		Employee employee = database.getEmployee(testEmployee.getId());
		assertEquals(4000, ((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary());
	}

	@Test
	public void testChangeHourlyWage() throws Exception {
		// GIVEN
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentClassification(database.factory().hourlyPaymentClassification(60));
		database.addEmployee(testEmployee);

		// WHEN
		{
			Employee employee = database.getEmployee(testEmployee.getId());
			((HourlyPaymentClassification) employee.getPaymentClassification()).setHourlyWage(40);
		}

		// THEN
		Employee employee = database.getEmployee(testEmployee.getId());
		assertEquals(40, ((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage());
	}

	@Test
	public void testClearEmployees() throws Exception {
		int employeeId = testEmployee().getId();
		database.addEmployee(testEmployee());
		assertNotNull(database.getEmployee(employeeId));

		EntityTransaction transaction = database.getTransaction();
		database.deleteAllEmployees();
		transaction.commit();

		assertNull(database.getEmployee(employeeId));
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		database.addEmployee(testEmployee());
		assertNotNull(database.getEmployee(testEmployee().getId()));

		database.deleteEmployee(testEmployee().getId());

		assertNull(database.getEmployee(testEmployee().getId()));
	}

	@Test
	public void testChangeEmployeeName() throws Exception {
		database.addEmployee(testEmployee());

		// EntityTransaction transaction = payrollDatabase.getTransaction();
		{
			Employee employee = database.getEmployee(testEmployee().getId());
			employee.setName("Bela");
		}
		// transaction.commit();

		Employee employee = database.getEmployee(testEmployee().getId());
		assertEquals(employee.getName(), "Bela");

	}

	@Test
	public void testGetAllEmployees() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		database.addEmployee(testEmployee());
		database.addEmployee(testEmployee2());
		transaction.commit();

		Collection<Employee> employees = database.getAllEmployees();
		assertEquals(2, employees.size());

	}
	
	@Test
	public void testAddTimeCard() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		Employee testEmployeeWithTimeCard = testEmployeeWithOneTimeCard();
		
		database.addEmployee(testEmployeeWithTimeCard);
		transaction.commit();

		Employee employee = database.getEmployee(testEmployeeWithTimeCard.getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCardsIn(DateInterval.of(THIS_FRIDAY, THIS_FRIDAY)));
		assertThat(timeCard, notNullValue());
		assertThat(timeCard.getWorkingHourQty(), is(8));
	}

	private Employee testEmployeeWithOneTimeCard() {
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentSchedule(database.factory().weeklyPaymentSchedule());
		HourlyPaymentClassification hourlyPaymentClassification = database.factory().hourlyPaymentClassification(0);
		testEmployee.setPaymentClassification(hourlyPaymentClassification);
		hourlyPaymentClassification.addTimeCard(database.factory().timeCard(THIS_FRIDAY, 8));
//		((HourlyPaymentClassification) testEmployee.getPaymentClassification()).addTimeCard(database.create().timeCard(THIS_FRIDAY, 8));
		return testEmployee;
	}

	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}

}