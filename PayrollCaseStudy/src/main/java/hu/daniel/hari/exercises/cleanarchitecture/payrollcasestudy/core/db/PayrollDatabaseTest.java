package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.db;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Objects;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

import org.junit.After;
import org.junit.Test;

public class PayrollDatabaseTest {
	
	private PayrollDatabase payrollDatabase = PayrollDatabase.get();

	@Test
	public void testAddEmployee() {
		
		assertNotNull(payrollDatabase);
		
		int employeeId = 1;
		Employee employee = createTestEmployee(employeeId);
		payrollDatabase.addEmployee(employee);
		
		Employee returnedEmployee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(returnedEmployee);
		assertEquals(employee.name, returnedEmployee.name);
		
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
	public void clean() {
		PayrollDatabase.get().clearEmployees();
	}

	private Employee createTestEmployee(int employeeId) {
		Employee employee = new Employee();
		employee.id = employeeId;
		employee.name = "Bob";
		return employee;
	}

	
}
