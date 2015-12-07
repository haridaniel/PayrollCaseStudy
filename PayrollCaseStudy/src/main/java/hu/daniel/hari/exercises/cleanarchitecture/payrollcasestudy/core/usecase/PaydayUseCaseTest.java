package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.CurrentTestScope;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Test;

//@Ignore
public class PaydayUseCaseTest {
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	
	private static final LocalDate LAST_FRIDAY = LocalDate.of(2015, 11, 27);
	private static final LocalDate LAST_SATURDAY = LocalDate.of(2015, 11, 28);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	private static final LocalDate THIS_SATURDAY = LocalDate.of(2015, 12, 05);
	
	private PayrollDatabase payrollDatabase = CurrentTestScope.DATABASE;
	
	@After
	public void clearDatabase() {
		EntityTransaction transaction = payrollDatabase.getTransaction();
		payrollDatabase.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testPaySingleSalariedEmployee() throws Exception {
		//GIVEN
		new AddSalariedEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				1000).execute();

		//WHEN
		LocalDate date = LAST_DAY_OF_A_MONTH;
		PaydayUseCase paydayUseCase = new PaydayUseCase(payrollDatabase, date);
		paydayUseCase.execute();
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.amount, is(1000));
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCard() throws Exception {
		//GIVEN
		
		new AddHourlyEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				42).execute();
		//WHEN
		LocalDate date = THIS_FRIDAY;
		PaydayUseCase paydayUseCase = new PaydayUseCase(payrollDatabase, date);
		paydayUseCase.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.amount, is(0));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				10).execute();
		
		new AddTimeCardUseCase(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_FRIDAY, 
				8)).execute();
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(payrollDatabase, THIS_FRIDAY); //THIS_FRIDAY
		paydayUseCase.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.amount, is(80));
	}

	@Test
	public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				10).execute();
		
		new AddTimeCardUseCase(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), LAST_SATURDAY, 
				4)).execute();
		new AddTimeCardUseCase(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_FRIDAY, 
				8)).execute();
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(payrollDatabase, THIS_FRIDAY); //THIS_FRIDAY
		paydayUseCase.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.amount, is(120));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeThreeTimeCardsSpanningTwoPayPeriods() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(payrollDatabase, testEmployee().getId(), testEmployee().getName(), testEmployee().getAddress(), 
				10).execute();
		
		new AddTimeCardUseCase(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), LAST_FRIDAY, 
				4)).execute(); //This is previous pay period, should be ignored
		new AddTimeCardUseCase(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_FRIDAY, 
				8)).execute(); //This is in this pay period
		new AddTimeCardUseCase(payrollDatabase, new AddTimeCardRequestModel(testEmployee().getId(), THIS_SATURDAY, 
				4)).execute(); //This is next pay period, should be ignored
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(payrollDatabase, THIS_FRIDAY);
		paydayUseCase.execute();
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.amount, is(80));
	}
	
	private PayCheck getSinglePaycheck(PaydayUseCase paydayUseCase) {
		Collection<PayCheck> payChecks = paydayUseCase.getPayChecks();
		System.out.println(payChecks);
		assertThat(payChecks.size(), is(1));
		return payChecks.iterator().next();
	}

	private Employee testEmployee() {
		Employee employee = payrollDatabase.factory().employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}
	
	
}
