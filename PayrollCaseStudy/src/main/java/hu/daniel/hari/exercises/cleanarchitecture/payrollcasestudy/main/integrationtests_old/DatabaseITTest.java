package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old;

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

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway.NoEmployeeWithSuchUnionMemberIdException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway.NoSuchEmployeeException;

public class DatabaseITTest extends AbstractDatabaseITTest {
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	
	private Database database;
	private EmployeeGateway employeeGateway;
	private TransactionalRunner transactionalRunner;
	private EntityFactory entityFactory;

	public DatabaseITTest(Database database) {
		this.database = database;
		employeeGateway = database.employeeGateway();
		transactionalRunner = database.transactionalRunner();
		entityFactory = database.entityFactory();
	}
	
	@Before
	public void clearDatabaseInTransaction() {
		transactionalRunner.executeInTransaction(() -> {
			employeeGateway.deleteAll();
		});
	}
	
	@Ignore
	@Test
	public void testTransactionRollback() throws Exception {
		try {
			transactionalRunner.executeInTransaction(() -> {
				employeeGateway.addNew(employee());
				employeeGateway.addNew(employee());
			});
			fail("Should have thrown exception for duplicate id");
		} catch (RuntimeException e) {
		}
		assertThat(employeeGateway.findAll().size(), is(0));
	}

	@Test
	public void testAddEmployee() {
		int employeeId = employee().getId();
		Employee employee = employee();
		employeeGateway.addNew(employee);

		Employee returnedEmployee = employeeGateway.findById(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.getName(), returnedEmployee.getName());

	}

	@Test
	public void testAddSalariedAndHourlyEmployee() throws Exception {
		Employee testEmployee = employee();
		Employee testEmployee2 = employee2();
		
		transactionalRunner.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(entityFactory.salariedPaymentClassification(5566));
			employeeGateway.addNew(testEmployee);
			
			testEmployee2.setPaymentClassification(entityFactory.hourlyPaymentClassification(13));
			employeeGateway.addNew(testEmployee2);
		});
		
		{
			Employee employee = employeeGateway.findById(testEmployee.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((SalariedPaymentClassification) paymentClassification).getMonthlySalary(), 5566);
		}

		{
			Employee employee = employeeGateway.findById(testEmployee2.getId());
			PaymentClassification paymentClassification = employee.getPaymentClassification();
			assertEquals(((HourlyPaymentClassification) paymentClassification).getHourlyWage(), 13);
		}

	}

	@Test
	public void testChangeMonthlySalary() throws Exception {
		// GIVEN
		Employee testEmployee = employee();
		transactionalRunner.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(entityFactory.salariedPaymentClassification(5566));
			employeeGateway.addNew(testEmployee);
		});

		// WHEN
		{
			Employee employee = employeeGateway.findById(testEmployee.getId());
			((SalariedPaymentClassification) employee.getPaymentClassification()).setMonthlySalary(4000);
		}

		// THEN
		Employee employee = employeeGateway.findById(testEmployee.getId());
		assertEquals(4000, ((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary());
	}

	@Test
	public void testChangeHourlyWage() throws Exception {
		// GIVEN
		Employee testEmployee = employee();
		transactionalRunner.executeInTransaction(() -> {
			testEmployee.setPaymentClassification(entityFactory.hourlyPaymentClassification(60));
			employeeGateway.addNew(testEmployee);
		});

		// WHEN
		{
			Employee employee = employeeGateway.findById(testEmployee.getId());
			((HourlyPaymentClassification) employee.getPaymentClassification()).setHourlyWage(40);
		}

		// THEN
		Employee employee = employeeGateway.findById(testEmployee.getId());
		assertEquals(40, ((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage());
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testClearDatabase() throws Exception {
		int employeeId = employee().getId();
		transactionalRunner.executeInTransaction(() -> {
			employeeGateway.addNew(employee());
		});
		assertNotNull(employeeGateway.findById(employeeId));

		clearDatabaseInTransaction();

		employeeGateway.findById(employeeId);
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testDeleteEmployee() throws Exception {
		employeeGateway.addNew(employee());
		assertNotNull(employeeGateway.findById(employee().getId()));

		employeeGateway.deleteById(employee().getId());

		employeeGateway.findById(employee().getId());
	}

	@Test
	public void testChangeEmployeeName() throws Exception {
		employeeGateway.addNew(employee());

		{
//			EntityTransaction transaction = database.getTransaction();
			Employee employee = employeeGateway.findById(employee().getId());
			employee.setName("Bela");
//			transaction.commit();
		}

		Employee employee = employeeGateway.findById(employee().getId());
		assertEquals(employee.getName(), "Bela");

	}

	@Test
	public void testGetAllEmployees() throws Exception {
		transactionalRunner.executeInTransaction(() -> {
			employeeGateway.addNew(employee());
			employeeGateway.addNew(employee2());
		});
		Collection<Employee> employees = employeeGateway.findAll();
		assertEquals(2, employees.size());
	}
	
	@Test
	public void testAddTimeCard() throws Exception {
		Employee testEmployeeWithTimeCard = employeeWithOneTimeCard();
		transactionalRunner.executeInTransaction(() -> {
			employeeGateway.addNew(testEmployeeWithTimeCard);
		});
		
		database.getEntityManager().clear();

		Employee employee = employeeGateway.findById(testEmployeeWithTimeCard.getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCardsIn(DateInterval.of(THIS_FRIDAY, THIS_FRIDAY)));
		assertThat(timeCard, notNullValue());
		assertThat(timeCard.getWorkingHourQty(), is(8));
	}
	
	@Test
	public void testGetEmployeeIdByUnionMemberId() throws Exception {
		Employee employee = employee();
		employee.setAffiliation(entityFactory.unionMemberAffiliation(7000, 0));
		
		transactionalRunner.executeInTransaction(() -> {
			employeeGateway.addNew(employee);
		});
		
		assertThat(employeeGateway.findByUnionMemberId(7000), is(employee.getId()));
	}
	
	@Test(expected = NoEmployeeWithSuchUnionMemberIdException.class)
	public void testGetEmployeeIdByUnionMemberId_WithWrongId_ShouldThrow() throws Exception {
		employeeGateway.findByUnionMemberId(7999);
	}

	private Employee employee() {
		Employee employee = entityFactory.employee();
		employee.setId(1);
		employee.setName("Bob");
		employee.setPaymentMethod(entityFactory.holdPaymentMethod());
		return employee;
	}

	private Employee employee2() {
		Employee employee = entityFactory.employee();
		employee.setId(2);
		employee.setName("Robert");
		return employee;
	}

	private Employee employeeWithOneTimeCard() {
		Employee testEmployee = employee();
		testEmployee.setPaymentSchedule(entityFactory.weeklyPaymentSchedule());
		HourlyPaymentClassification hourlyPaymentClassification = entityFactory.hourlyPaymentClassification(0);
		testEmployee.setPaymentClassification(hourlyPaymentClassification);
		hourlyPaymentClassification.addTimeCard(entityFactory.timeCard(THIS_FRIDAY, 8));
		return testEmployee;
	}
	
	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}

}