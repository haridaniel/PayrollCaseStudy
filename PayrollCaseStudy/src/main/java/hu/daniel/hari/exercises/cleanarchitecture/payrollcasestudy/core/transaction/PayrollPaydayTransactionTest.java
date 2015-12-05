package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Test;

public class PayrollPaydayTransactionTest {
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	
	private static final LocalDate LAST_FRIDAY = LocalDate.of(2015, 11, 27);
	private static final LocalDate LAST_SATURDAY = LocalDate.of(2015, 11, 28);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	private static final LocalDate THIS_SATURDAY = LocalDate.of(2015, 12, 05);
	
	private PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();
//	private PayrollDatabase payrollDatabase = new JPAPayrollDatabaseModule().getPayrollDatabase();

	@After
	public void clearDatabase() {
		EntityTransaction transaction = payrollDatabase.createTransaction();
		payrollDatabase.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testPaySingleSalariedEmployee() throws Exception {
		//GIVEN
		new AddSalariedEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				1000).execute();

		//WHEN
		LocalDate date = LAST_DAY_OF_A_MONTH;
		PaydayTransaction paydayTransaction = new PaydayTransaction(payrollDatabase, date);
		paydayTransaction.execute();
		
		PayCheck payCheck = getSinglePaycheck(paydayTransaction);
		assertThat(payCheck.amount, is(1000));
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCard() throws Exception {
		//GIVEN
		
		new AddHourlyEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				42).execute();
		//WHEN
		LocalDate date = THIS_FRIDAY;
		PaydayTransaction paydayTransaction = new PaydayTransaction(payrollDatabase, date);
		paydayTransaction.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayTransaction);
		assertThat(payCheck.amount, is(0));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
		//GIVEN
		new AddHourlyEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				10).execute();
		
		new AddTimeCardTransaction(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_FRIDAY, 
				8)).execute();
		
		//WHEN
		PaydayTransaction paydayTransaction = new PaydayTransaction(payrollDatabase, THIS_FRIDAY); //THIS_FRIDAY
		paydayTransaction.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayTransaction);
		assertThat(payCheck.amount, is(80));
	}

	@Test
	public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
		//GIVEN
		new AddHourlyEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				10).execute();
		
		new AddTimeCardTransaction(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), LAST_SATURDAY, 
				4)).execute();
		new AddTimeCardTransaction(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_FRIDAY, 
				8)).execute();
		
		//WHEN
		PaydayTransaction paydayTransaction = new PaydayTransaction(payrollDatabase, THIS_FRIDAY); //THIS_FRIDAY
		paydayTransaction.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayTransaction);
		assertThat(payCheck.amount, is(120));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeThreeTimeCardsSpanningTwoPayPeriods() throws Exception {
		//GIVEN
		new AddHourlyEmployeeTransaction(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				10).execute();
		
		new AddTimeCardTransaction(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), LAST_FRIDAY, 
				4)).execute(); //This is previous pay period, should be ignored
		new AddTimeCardTransaction(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_FRIDAY, 
				8)).execute(); //This is in this pay period
		new AddTimeCardTransaction(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_SATURDAY, 
				4)).execute(); //This is next pay period, should be ignored
		
		//WHEN
		PaydayTransaction paydayTransaction = new PaydayTransaction(payrollDatabase, THIS_FRIDAY);
		paydayTransaction.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayTransaction);
		assertThat(payCheck.amount, is(80));
	}
	
	private PayCheck getSinglePaycheck(PaydayTransaction paydayTransaction) {
		Collection<PayCheck> payChecks = paydayTransaction.getPayChecks();
		System.out.println(payChecks);
		assertThat(payChecks.size(), is(1));
		return payChecks.iterator().next();
	}

	private static Employee testEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}
	
	
}
