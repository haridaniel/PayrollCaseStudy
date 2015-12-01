package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

import org.junit.After;
import org.junit.Test;

public abstract class PayrollDatabaseTest {

	private PayrollDatabase payrollDatabase;

	public PayrollDatabaseTest(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

	@Test
	public void testAddEmployee() {
		
		assertNotNull(payrollDatabase);
		
		int employeeId = 1;
		Employee employee = createTestEmployee(employeeId);
		payrollDatabase.addEmployee(employee);
		
		Employee returnedEmployee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.getName(), returnedEmployee.getName());
		
	}

	@Test
	public void testClearEmployees() throws Exception {
		int employeeId = 2;
		payrollDatabase.addEmployee(createTestEmployee(employeeId));
		assertNotNull(payrollDatabase.getEmployee(employeeId));
		
		payrollDatabase.clearEmployees();
		
		assertNull(payrollDatabase.getEmployee(employeeId));
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		int employeeId = 2;
		payrollDatabase.addEmployee(createTestEmployee(employeeId));
		assertNotNull(payrollDatabase.getEmployee(employeeId));
		
		payrollDatabase.deleteEmployee(employeeId);
		
		assertNull(payrollDatabase.getEmployee(employeeId));
	}

	@After
	public void clearDatabase() {
		payrollDatabase.clearEmployees();
	}

	private Employee createTestEmployee(int employeeId) {
		Employee employee = new Employee();
		employee.id = employeeId;
		employee.setName("Bob");
		return employee;
	}

}