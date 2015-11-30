package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.AddHourlyEmployeeTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.AddSalariedEmployeeTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.DeleteEmployeeTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction.Transaction;

import org.junit.After;
import org.junit.Test;

public class PayrollTest {

	private PayrollDatabase payrollDatabase = PayrollDatabase.get();

	@Test
	public void testAddSalariedEmployeeTransaction() throws Exception {

		int employeeId = 1;
		int monthlySalary = 150000;
		Transaction transaction = new AddSalariedEmployeeTransaction(employeeId, "Bob", "Home", monthlySalary);
		transaction.execute();

		Employee employee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(employee);
		assertEquals(employee.name, "Bob");
		assertThat(employee.paymentMethod, instanceOf(HoldPaymentMethod.class));
		
		assertThat(employee.paymentClassification, instanceOf(SalariedPaymentClassification.class));
		assertThat(((SalariedPaymentClassification) employee.paymentClassification).monthlySalary, is(monthlySalary));
		assertThat(employee.paymentSchedule, instanceOf(MontlhyPaymentSchedule.class));
		
	}
	
	@Test
	public void testAddHourlyRatedEmployeeTransaction() throws Exception {
		
		int employeeId = 1;
		int hourlyRate = 100;
		
		Transaction transaction = new AddHourlyEmployeeTransaction(employeeId, "Bob", "Home", hourlyRate);
		transaction.execute();
		
		Employee employee = payrollDatabase.getEmployee(employeeId);
		assertNotNull(employee);
		assertEquals(employee.name, "Bob");
		assertThat(employee.paymentMethod, instanceOf(HoldPaymentMethod.class));
		
		assertThat(employee.paymentClassification, instanceOf(HourlyPaymentClassification.class));
		assertThat(((HourlyPaymentClassification) employee.paymentClassification).hourlyRate, is(hourlyRate));
		assertThat(employee.paymentSchedule, instanceOf(WeeklyPaymentSchedule.class));
		
	}

	@Test
	public void testDeleteEmployeeTransaction() throws Exception {
		int employeeId = 1;
		Employee employee = createTestEmployee(employeeId);
		payrollDatabase.addEmployee(employee);
		
		assertNotNull(payrollDatabase.getEmployee(employeeId));
		
		DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(employeeId);
		deleteEmployeeTransaction.execute();
		
		assertNull(payrollDatabase.getEmployee(employeeId));
		
	}

	
	@After
	public void cleanPayrollDatabase() {
		payrollDatabase.clearEmployees();
	}
	
	private static Employee createTestEmployee(int employeeId) {
		Employee employee = new Employee();
		employee.id = employeeId;
		employee.name = "Boob";
		return employee;
	}
}
