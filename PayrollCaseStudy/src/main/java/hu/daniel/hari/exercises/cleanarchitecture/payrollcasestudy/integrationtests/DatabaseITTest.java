package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway.NoEmployeeWithSuchUnionMemberIdException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway.NoSuchEmployeeException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.AllEntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class DatabaseITTest extends AbstractDatabaseITTest {
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	
	private Database database;
	private EntityGateway entityGateway;
	private TransactionalRunner transactionalRunner;
	private AllEntityFactory allEntityFactory;

	public DatabaseITTest(Database database) {
		this.database = database;
		entityGateway = database.getEntityGateway();
		transactionalRunner = database.getTransactionalRunner();
		allEntityFactory = database.allEntityFactory();
	}
	
	@Before
	public void clearDatabaseInTransaction() {
		transactionalRunner.executeInTransaction(() -> {
			entityGateway.deleteAllEmployees();
		});
	}
	
	@Ignore
	@Test
	public void testTransactionRollback() throws Exception {
		try {
			transactionalRunner.executeInTransaction(() -> {
				entityGateway.addEmployee(employee());
				entityGateway.addEmployee(employee());
			});
			fail("Should have thrown exception for duplicate id");
		} catch (RuntimeException e) {
		}
		assertThat(entityGateway.getAllEmployees().size(), is(0));
	}

	@Test
	public void testAddEmployee() {
		int employeeId = employee().getId();
		Employee employee = employee();
		entityGateway.addEmployee(employee);

		Employee returnedEmployee = entityGateway.getEmployee(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.getName(), returnedEmployee.getName());

	}

	@Test
	public void testAddSalariedAndHourlyEmployee() throws Exception {
		Employee testEmployee = employee();
		Employee testEmployee2 = employee2();
		
		transactionalRunner.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(allEntityFactory.salariedPaymentClassification(5566));
			entityGateway.addEmployee(testEmployee);
			
			testEmployee2.setPaymentClassification(allEntityFactory.hourlyPaymentClassification(13));
			entityGateway.addEmployee(testEmployee2);
		});
		
		{
			Employee employee = entityGateway.getEmployee(testEmployee.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((SalariedPaymentClassification) paymentClassification).getMonthlySalary(), 5566);
		}

		{
			Employee employee = entityGateway.getEmployee(testEmployee2.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((HourlyPaymentClassification) paymentClassification).getHourlyWage(), 13);
		}

	}

	@Test
	public void testChangeMonthlySalary() throws Exception {
		// GIVEN
		Employee testEmployee = employee();
		transactionalRunner.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(allEntityFactory.salariedPaymentClassification(5566));
			entityGateway.addEmployee(testEmployee);
		});

		// WHEN
		{
			Employee employee = entityGateway.getEmployee(testEmployee.getId());
			((SalariedPaymentClassification) employee.getPaymentClassification()).setMonthlySalary(4000);
		}

		// THEN
		Employee employee = entityGateway.getEmployee(testEmployee.getId());
		assertEquals(4000, ((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary());
	}

	@Test
	public void testChangeHourlyWage() throws Exception {
		// GIVEN
		Employee testEmployee = employee();
		transactionalRunner.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(allEntityFactory.hourlyPaymentClassification(60));
			entityGateway.addEmployee(testEmployee);
		});

		// WHEN
		{
			Employee employee = entityGateway.getEmployee(testEmployee.getId());
			((HourlyPaymentClassification) employee.getPaymentClassification()).setHourlyWage(40);
		}

		// THEN
		Employee employee = entityGateway.getEmployee(testEmployee.getId());
		assertEquals(40, ((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage());
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testClearDatabase() throws Exception {
		int employeeId = employee().getId();
		transactionalRunner.executeInTransaction(() -> {
			entityGateway.addEmployee(employee());
		});
		assertNotNull(entityGateway.getEmployee(employeeId));

		clearDatabaseInTransaction();

		entityGateway.getEmployee(employeeId);
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testDeleteEmployee() throws Exception {
		entityGateway.addEmployee(employee());
		assertNotNull(entityGateway.getEmployee(employee().getId()));

		entityGateway.deleteEmployee(employee().getId());

		entityGateway.getEmployee(employee().getId());
	}

	@Test
	public void testChangeEmployeeName() throws Exception {
		entityGateway.addEmployee(employee());

		{
//			EntityTransaction transaction = database.getTransaction();
			Employee employee = entityGateway.getEmployee(employee().getId());
			employee.setName("Bela");
//			transaction.commit();
		}

		Employee employee = entityGateway.getEmployee(employee().getId());
		assertEquals(employee.getName(), "Bela");

	}

	@Test
	public void testGetAllEmployees() throws Exception {
		transactionalRunner.executeInTransaction(() -> {
			entityGateway.addEmployee(employee());
			entityGateway.addEmployee(employee2());
		});
		Collection<Employee> employees = entityGateway.getAllEmployees();
		assertEquals(2, employees.size());
	}
	
	@Test
	public void testAddTimeCard() throws Exception {
		Employee testEmployeeWithTimeCard = employeeWithOneTimeCard();
		transactionalRunner.executeInTransaction(() -> {
			entityGateway.addEmployee(testEmployeeWithTimeCard);
		});
		
		database.getEntityManager().clear();

		Employee employee = entityGateway.getEmployee(testEmployeeWithTimeCard.getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCardsIn(DateInterval.of(THIS_FRIDAY, THIS_FRIDAY)));
		assertThat(timeCard, notNullValue());
		assertThat(timeCard.getWorkingHourQty(), is(8));
	}
	
	@Test
	public void testGetEmployeeIdByUnionMemberId() throws Exception {
		Employee employee = employee();
		employee.setAffiliation(allEntityFactory.unionMemberAffiliation(7000, 0));
		
		transactionalRunner.executeInTransaction(() -> {
			entityGateway.addEmployee(employee);
		});
		
		assertThat(entityGateway.getEmployeeIdByUnionMemberId(7000), is(employee.getId()));
	}
	
	@Test(expected = NoEmployeeWithSuchUnionMemberIdException.class)
	public void testGetEmployeeIdByUnionMemberId_WithWrongId_ShouldThrow() throws Exception {
		entityGateway.getEmployeeIdByUnionMemberId(7999);
	}

	private Employee employee() {
		Employee employee = allEntityFactory.employee();
		employee.setId(1);
		employee.setName("Bob");
		employee.setPaymentMethod(allEntityFactory.holdPaymentMethod());
		return employee;
	}

	private Employee employee2() {
		Employee employee = allEntityFactory.employee();
		employee.setId(2);
		employee.setName("Robert");
		return employee;
	}

	private Employee employeeWithOneTimeCard() {
		Employee testEmployee = employee();
		testEmployee.setPaymentSchedule(allEntityFactory.weeklyPaymentSchedule());
		HourlyPaymentClassification hourlyPaymentClassification = allEntityFactory.hourlyPaymentClassification(0);
		testEmployee.setPaymentClassification(hourlyPaymentClassification);
		hourlyPaymentClassification.addTimeCard(allEntityFactory.timeCard(THIS_FRIDAY, 8));
		return testEmployee;
	}
	
	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}

}