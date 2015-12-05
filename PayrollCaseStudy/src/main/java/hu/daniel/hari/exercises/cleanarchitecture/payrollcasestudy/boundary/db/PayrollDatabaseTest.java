package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;

import org.junit.After;
import org.junit.Test;

public abstract class PayrollDatabaseTest {
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);

	private PayrollDatabase payrollDatabase;

	public PayrollDatabaseTest(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

	private static Employee testEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Bob");
		return employee;
	}

	private static Employee testEmployee2() {
		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("Robert");
		return employee;
	}

	private static Employee testEmployeeWithTimeCard() {
		Employee testEmployee = testEmployee();
		HourlyPaymentClassification hourlyPaymentClassification = new HourlyPaymentClassification(0);
		hourlyPaymentClassification.addTimeCard(new TimeCard(THIS_FRIDAY, 8));
		testEmployee.setPaymentClassification(hourlyPaymentClassification);
		return testEmployee;
	}

	@Test
	public void testTransaction() throws Exception {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.addEmployee(testEmployee());
		payrollDatabase.addEmployee(testEmployee2());
		transaction.commit();
	}

	@After
	public void clearDatabase() {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testAddEmployee() {
		int employeeId = testEmployee().getId();
		Employee employee = testEmployee();
		payrollDatabase.addEmployee(employee);

		Employee returnedEmployee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.getName(), returnedEmployee.getName());

	}

	@Test
	public void testAddSalariedAndHourlyEmployee() throws Exception {
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentClassification(new SalariedPaymentClassification(5566));
		payrollDatabase.addEmployee(testEmployee);

		Employee testEmployee2 = testEmployee2();
		testEmployee2.setPaymentClassification(new HourlyPaymentClassification(13));
		payrollDatabase.addEmployee(testEmployee2);

		{
			Employee employee = payrollDatabase.getEmployee(testEmployee.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((SalariedPaymentClassification) paymentClassification).getMonthlySalary(), 5566);
		}

		{
			Employee employee = payrollDatabase.getEmployee(testEmployee2.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((HourlyPaymentClassification) paymentClassification).getHourlyWage(), 13);
		}

	}

	@Test
	public void testChangeMonthlySalary() throws Exception {
		// GIVEN
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentClassification(new SalariedPaymentClassification(5566));
		payrollDatabase.addEmployee(testEmployee);

		// WHEN
		{
			Employee employee = payrollDatabase.getEmployee(testEmployee.getId());
			((SalariedPaymentClassification) employee.getPaymentClassification()).setMonthlySalary(4000);
		}

		// THEN
		Employee employee = payrollDatabase.getEmployee(testEmployee.getId());
		assertEquals(4000, ((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary());
	}

	@Test
	public void testChangeHourlyWage() throws Exception {
		// GIVEN
		Employee testEmployee = testEmployee();
		testEmployee.setPaymentClassification(new HourlyPaymentClassification(60));
		payrollDatabase.addEmployee(testEmployee);

		// WHEN
		{
			Employee employee = payrollDatabase.getEmployee(testEmployee.getId());
			((HourlyPaymentClassification) employee.getPaymentClassification()).setHourlyWage(40);
		}

		// THEN
		Employee employee = payrollDatabase.getEmployee(testEmployee.getId());
		assertEquals(40, ((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage());
	}

	@Test
	public void testClearEmployees() throws Exception {
		int employeeId = testEmployee().getId();
		payrollDatabase.addEmployee(testEmployee());
		assertNotNull(payrollDatabase.getEmployee(employeeId));

		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.deleteAllEmployees();
		transaction.commit();

		assertNull(payrollDatabase.getEmployee(employeeId));
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		payrollDatabase.addEmployee(testEmployee());
		assertNotNull(payrollDatabase.getEmployee(testEmployee().getId()));

		payrollDatabase.deleteEmployee(testEmployee().getId());

		assertNull(payrollDatabase.getEmployee(testEmployee().getId()));
	}

	@Test
	public void testChangeEmployeeName() throws Exception {
		payrollDatabase.addEmployee(testEmployee());

		// EntityTransaction transaction = payrollDatabase.getTransaction();
		{
			Employee employee = payrollDatabase.getEmployee(testEmployee().getId());
			employee.setName("Bela");
		}
		// transaction.commit();

		Employee employee = payrollDatabase.getEmployee(testEmployee().getId());
		assertEquals(employee.getName(), "Bela");

	}

	@Test
	public void testGetAllEmployees() throws Exception {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.addEmployee(testEmployee());
		payrollDatabase.addEmployee(testEmployee2());
		transaction.commit();

		Collection<Employee> employees = payrollDatabase.getAllEmployees();
		assertEquals(2, employees.size());

	}
	
	@Test
	public void testAddTimeCard() throws Exception {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.addEmployee(testEmployeeWithTimeCard());
		transaction.commit();

		Employee employee = payrollDatabase.getEmployee(testEmployeeWithTimeCard().getId());
		TimeCard timeCard = ((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCard(THIS_FRIDAY);
		assertThat(timeCard, notNullValue());
		System.out.println();
	}

}