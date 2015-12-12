package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase.NoEmployeeWithSuchUnionMemberIdException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase.NoSuchEmployeeException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.AffiliationProxy;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class DatabaseITTest extends AbstractDatabaseITTest {
	
	public DatabaseITTest(PayrollDatabase payrollDatabase) {
		super(payrollDatabase);
	}

	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	
	@Before
	public void clearDatabase() {
		database.executeInTransaction(() -> 
			database.clearDatabase()
		);
	}
	
	@Ignore
	@Test
	public void testTransactionRollback() throws Exception {
		try {
			database.executeInTransaction(() -> {
				database.addEmployee(employee());
				database.addEmployee(employee());
			});
			fail("Should have thrown exception for duplicate id");
		} catch (RuntimeException e) {
		}
		assertThat(database.getAllEmployees().size(), is(0));
	}

	@Test
	public void testAddEmployee() {
		int employeeId = employee().getId();
		Employee employee = employee();
		database.addEmployee(employee);

		Employee returnedEmployee = database.getEmployee(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.getName(), returnedEmployee.getName());

	}

	@Test
	public void testAddSalariedAndHourlyEmployee() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		Employee testEmployee = employee();
		testEmployee.setPaymentClassification(database.factory().salariedPaymentClassification(5566));
		database.addEmployee(testEmployee);

		Employee testEmployee2 = employee2();
		testEmployee2.setPaymentClassification(database.factory().hourlyPaymentClassification(13));
		database.addEmployee(testEmployee2);
		transaction.commit();
		
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
		Employee testEmployee = employee();
		database.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(database.factory().salariedPaymentClassification(5566));
			database.addEmployee(testEmployee);
		});

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
		Employee testEmployee = employee();
		database.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(database.factory().hourlyPaymentClassification(60));
			database.addEmployee(testEmployee);
		});

		// WHEN
		{
			Employee employee = database.getEmployee(testEmployee.getId());
			((HourlyPaymentClassification) employee.getPaymentClassification()).setHourlyWage(40);
		}

		// THEN
		Employee employee = database.getEmployee(testEmployee.getId());
		assertEquals(40, ((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage());
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testClearDatabase() throws Exception {
		int employeeId = employee().getId();
		database.addEmployee(employee());
		assertNotNull(database.getEmployee(employeeId));

		EntityTransaction transaction = database.getTransaction();
		database.clearDatabase();
		transaction.commit();

		database.getEmployee(employeeId);
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testDeleteEmployee() throws Exception {
		database.addEmployee(employee());
		assertNotNull(database.getEmployee(employee().getId()));

		database.deleteEmployee(employee().getId());

		database.getEmployee(employee().getId());
	}

	@Test
	public void testChangeEmployeeName() throws Exception {
		database.addEmployee(employee());

		{
//			EntityTransaction transaction = database.getTransaction();
			Employee employee = database.getEmployee(employee().getId());
			employee.setName("Bela");
//			transaction.commit();
		}

		Employee employee = database.getEmployee(employee().getId());
		assertEquals(employee.getName(), "Bela");

	}

	@Test
	public void testGetAllEmployees() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		database.addEmployee(employee());
		database.addEmployee(employee2());
		transaction.commit();

		Collection<Employee> employees = database.getAllEmployees();
		assertEquals(2, employees.size());

	}
	
	@Test
	public void testAddTimeCard() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		Employee testEmployeeWithTimeCard = employeeWithOneTimeCard();
		
		database.addEmployee(testEmployeeWithTimeCard);
		transaction.commit();

		Employee employee = database.getEmployee(testEmployeeWithTimeCard.getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCardsIn(DateInterval.of(THIS_FRIDAY, THIS_FRIDAY)));
		assertThat(timeCard, notNullValue());
		assertThat(timeCard.getWorkingHourQty(), is(8));
	}
	
	@Test
	public void testGetEmployeeIdByUnionMemberId() throws Exception {
		EntityTransaction transaction = database.getTransaction();
		Employee employee = employee();
		employee.setAffiliation(database.factory().unionMemberAffiliation(7000, 0));
		database.addEmployee(employee);
		transaction.commit();
		
		assertThat(database.getEmployeeIdByUnionMemberId(7000), is(employee.getId()));
	}
	
	@Test(expected = NoEmployeeWithSuchUnionMemberIdException.class)
	public void testGetEmployeeIdByUnionMemberId_WithWrongId_ShouldThrow() throws Exception {
		database.getEmployeeIdByUnionMemberId(7999);
	}

	private Employee employee() {
		Employee employee = database.factory().employee();
		employee.setId(1);
		employee.setName("Bob");
		employee.setPaymentMethod(database.factory().holdPaymentMethod());
		return employee;
	}

	private Employee employee2() {
		Employee employee = database.factory().employee();
		employee.setId(2);
		employee.setName("Robert");
		return employee;
	}

	private Employee employeeWithOneTimeCard() {
		Employee testEmployee = employee();
		testEmployee.setPaymentSchedule(database.factory().weeklyPaymentSchedule());
		HourlyPaymentClassification hourlyPaymentClassification = database.factory().hourlyPaymentClassification(0);
		testEmployee.setPaymentClassification(hourlyPaymentClassification);
		hourlyPaymentClassification.addTimeCard(database.factory().timeCard(THIS_FRIDAY, 8));
		return testEmployee;
	}
	
	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}

}