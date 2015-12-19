package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.PaydayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.changeaffiliation.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PaydayUseCaseITTest extends AbstractDatabaseITTest {

	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	private static final LocalDate LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS = LocalDate.of(2015, 12, 31);
	
	private static final LocalDate LAST_FRIDAY = LocalDate.of(2015, 11, 27);
	private static final LocalDate LAST_SATURDAY = LocalDate.of(2015, 11, 28);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	private static final LocalDate THIS_SATURDAY = LocalDate.of(2015, 12, 05);
	
	private Database database;
	private EntityGateway entityGateway;
	private TransactionalRunner transactionalRunner;
	
	public PaydayUseCaseITTest(Database database) {
		this.database = database;
		entityGateway = database.getEntityGateway();
		transactionalRunner = database.getTransactionalRunner();
	}
	
	@Before
	public void clearDatabaseInTransaction() {
		transactionalRunner.executeInTransaction(() -> {
			entityGateway.deleteAllEmployees();
		});
	}

	@Test
	public void paySingleSalariedEmployee_OnNotPayday_ShouldNotCreatePayCheck() throws Exception {
		//GIVEN
		new AddSalariedEmployeeUseCase(database).execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 1000));

		//WHEN
		LocalDate notAPayDate = LAST_DAY_OF_A_MONTH.minusDays(5);
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(notAPayDate));
		
		assertTrue(paydayUseCase.getPayChecks().isEmpty());
	}
	
	@Test
	public void testPaySingleSalariedEmployee() throws Exception {
		//GIVEN
		new AddSalariedEmployeeUseCase(database).execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 1000));
		
		//WHEN
		LocalDate date = LAST_DAY_OF_A_MONTH;
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(date));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(1000));
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_WithoutSales() throws Exception {
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.0d;
		new AddCommissionedEmployeeUseCase(database).execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), biWeeklyBaseSalary, commissionRate));
		
		//WHEN
		LocalDate anEvenFriday = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY.plusDays(14 * 5);
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(anEvenFriday));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(70_000));
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_TwoSalesReceipt() throws Exception {
		//GIVEN
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.1d;
		LocalDate payDate = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY;
		LocalDate salesReceiptDate1 = payDate;
		LocalDate salesReceiptDate2 = payDate.minusDays(10);
		
		new AddCommissionedEmployeeUseCase(database).execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), biWeeklyBaseSalary, commissionRate));
		
		new AddSalesReceiptUseCase(database).execute(new AddSalesReceiptRequest(employee().getId(), salesReceiptDate1, 10000));
		new AddSalesReceiptUseCase(database).execute(new AddSalesReceiptRequest(employee().getId(), salesReceiptDate2, 10000));

		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(payDate));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(72_000));
	}

	@Test
	public void testPaySingleCommissionedEmployee_ThreeSalesReceiptsSpanningThreePayPeriod() throws Exception {
		//GIVEN
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.1d;
		LocalDate payDate = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY;
		LocalDate dateInPreviousPayPeriod = payDate.minusDays(14);
		LocalDate dateInPayPeriod = payDate.minusDays(13);//Only this should be included!
		LocalDate dateInNextPayPeriod = payDate.plusDays(1);
		
		new AddCommissionedEmployeeUseCase(database).execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), biWeeklyBaseSalary, commissionRate));
		
		new AddSalesReceiptUseCase(database).execute(new AddSalesReceiptRequest(employee().getId(), dateInPayPeriod, 25000));
		new AddSalesReceiptUseCase(database).execute(new AddSalesReceiptRequest(employee().getId(), dateInPreviousPayPeriod, 25000));
		new AddSalesReceiptUseCase(database).execute(new AddSalesReceiptRequest(employee().getId(), dateInNextPayPeriod, 25000));
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(payDate));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(72_500));
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCard() throws Exception {
		//GIVEN
		
		new AddHourlyEmployeeUseCase(database).execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 42));
		//WHEN
		LocalDate date = THIS_FRIDAY;
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(date));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(0));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(database).execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 10));
		
		new AddTimeCardUseCase(database).execute(new AddTimeCardRequest(employee().getId(), THIS_FRIDAY, 
						8));
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database); //THIS_FRIDAY
		paydayUseCase.execute(new PaydayRequest(THIS_FRIDAY));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(80));
	}

	@Test
	public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(database).execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 10));
		
		new AddTimeCardUseCase(database).execute(new AddTimeCardRequest(employee().getId(), LAST_SATURDAY, 
						4));
		new AddTimeCardUseCase(database).execute(new AddTimeCardRequest(employee().getId(), THIS_FRIDAY, 
						8));
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database); //THIS_FRIDAY
		paydayUseCase.execute(new PaydayRequest(THIS_FRIDAY));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(120));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeThreeTimeCardsSpanningTwoPayPeriods() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(database).execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 10));
		
		new AddTimeCardUseCase(database).execute(new AddTimeCardRequest(employee().getId(), LAST_FRIDAY, 
						4)); //This is previous pay period, should be ignored
		new AddTimeCardUseCase(database).execute(new AddTimeCardRequest(employee().getId(), THIS_FRIDAY, 
						8)); //This is in this pay period
		new AddTimeCardUseCase(database).execute(new AddTimeCardRequest(employee().getId(), THIS_SATURDAY, 
						4)); //This is next pay period, should be ignored
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(THIS_FRIDAY));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(80));
	}
	
	@Test
	public void testPaySingleSalariedEmployee_UnionMemberAffiliationWeeklyDues_Deducted() throws Exception {
		int weeklyDueAmount = 25;
		
		//GIVEN
		new AddSalariedEmployeeUseCase(database).execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		
		new AddUnionMemberAffiliationUseCase(database)
				.execute(new AddUnionMemberAffiliationRequest(employee().getId(), 0, weeklyDueAmount));
		
		
		//WHEN
		LocalDate date = LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS;
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(date));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getDeductionsAmount(), is(4 * 25));
	}
	
	@Test
	public void testPaySingleSalariedEmployee_UnionMemberAffiliationOneServiceCharge_Deducted() throws Exception {
		int unionMemberId = 7005;
		LocalDate date = LAST_DAY_OF_A_MONTH;
		
		//GIVEN
		new AddSalariedEmployeeUseCase(database).execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		new AddUnionMemberAffiliationUseCase(database)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, 0));
		new AddServiceChargeUseCase(database)
			.execute(new AddServiceChargeRequest(unionMemberId, date, 25));
		
		//SERVICECHARGES
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(date));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getDeductionsAmount(), is(25));
	}
	
	@Test
	public void testPaySingleSalariedEmployee_UnionMemberAffiliationOneServiceCharge_DeductedOfTwo_SpanningMultiplePayPeriods() throws Exception {
		int unionMemberId = 7005;
		LocalDate dateInPayPeriod = LAST_DAY_OF_A_MONTH;
		LocalDate dateNotInPayPeriod = LAST_DAY_OF_A_MONTH.plusDays(1);
		
		//GIVEN
		new AddSalariedEmployeeUseCase(database).execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		new AddUnionMemberAffiliationUseCase(database)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, 0));
		new AddServiceChargeUseCase(database)
			.execute(new AddServiceChargeRequest(unionMemberId, dateInPayPeriod, 25));
		new AddServiceChargeUseCase(database)
			.execute(new AddServiceChargeRequest(unionMemberId, dateNotInPayPeriod, 25));
		
		//SERVICECHARGES
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(database);
		paydayUseCase.execute(new PaydayRequest(dateInPayPeriod));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getDeductionsAmount(), is(25));
	}
	
	private PayCheck getSinglePaycheck(PaydayUseCase paydayUseCase) {
		Collection<PayCheck> payChecks = paydayUseCase.getPayChecks();
		System.out.println(payChecks);
		assertThat(payChecks.size(), is(1));
		return payChecks.iterator().next();
	}

	private Employee employee() {
		Employee employee = entityGateway.factory().employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}
	
	
}
