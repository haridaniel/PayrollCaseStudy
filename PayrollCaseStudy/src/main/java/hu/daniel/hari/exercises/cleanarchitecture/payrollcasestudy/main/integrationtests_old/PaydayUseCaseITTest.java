package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Constants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.PaydayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;

@Ignore
public class PaydayUseCaseITTest extends AbstractDatabaseITTest {

	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	private static final LocalDate LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS = LocalDate.of(2015, 12, 31);
	
	private static final LocalDate LAST_FRIDAY = LocalDate.of(2015, 11, 27);
	private static final LocalDate LAST_SATURDAY = LocalDate.of(2015, 11, 28);
	private static final LocalDate THIS_FRIDAY = LocalDate.of(2015, 12, 04);
	private static final LocalDate THIS_SATURDAY = LocalDate.of(2015, 12, 05);
	
	private Database database;
	private EmployeeGateway employeeGateway;
	private TransactionalRunner transactionalRunner;
	private EntityFactory entityFactory;
	
	public PaydayUseCaseITTest(Database database) {
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

	@Test
	public void paySingleSalariedEmployee_OnNotPayday_ShouldNotCreatePayCheck() throws Exception {
		//GIVEN
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 1000));

		//WHEN
		LocalDate notAPayDate = LAST_DAY_OF_A_MONTH.minusDays(5);
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
		paydayUseCase.execute(new PaydayRequest(notAPayDate));
		
		assertTrue(paydayUseCase.getPayChecks().isEmpty());
	}
	
	@Test
	public void testPaySingleSalariedEmployee() throws Exception {
		//GIVEN
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 1000));
		
		//WHEN
		LocalDate date = LAST_DAY_OF_A_MONTH;
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
		paydayUseCase.execute(new PaydayRequest(date));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(1000));
	}
	
	@Test
	public void testPaySingleCommissionedEmployee_WithoutSales() throws Exception {
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.0d;
		new AddCommissionedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), biWeeklyBaseSalary, commissionRate));
		
		//WHEN
		LocalDate anEvenFriday = Constants.BIWEEKLY_PAYMENT_SCHEDULE_REFERENCE_FRIDAY.plusDays(14 * 5);
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
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
		
		new AddCommissionedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), biWeeklyBaseSalary, commissionRate));
		
		new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory).execute(new AddSalesReceiptRequest(employee().getId(), salesReceiptDate1, 10000));
		new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory).execute(new AddSalesReceiptRequest(employee().getId(), salesReceiptDate2, 10000));

		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
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
		
		new AddCommissionedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), biWeeklyBaseSalary, commissionRate));
		
		new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory).execute(new AddSalesReceiptRequest(employee().getId(), dateInPayPeriod, 25000));
		new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory).execute(new AddSalesReceiptRequest(employee().getId(), dateInPreviousPayPeriod, 25000));
		new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory).execute(new AddSalesReceiptRequest(employee().getId(), dateInNextPayPeriod, 25000));
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
		paydayUseCase.execute(new PaydayRequest(payDate));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(72_500));
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCard() throws Exception {
		//GIVEN
		
		new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 42));
		//WHEN
		LocalDate date = THIS_FRIDAY;
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
		paydayUseCase.execute(new PaydayRequest(date));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(0));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 10));

		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory).execute(new AddTimeCardRequest(employee().getId(), THIS_FRIDAY, 
						8));
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway); //THIS_FRIDAY
		paydayUseCase.execute(new PaydayRequest(THIS_FRIDAY));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(80));
	}

	@Test
	public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 10));
		
		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory).execute(new AddTimeCardRequest(employee().getId(), LAST_SATURDAY, 
						4));
		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory).execute(new AddTimeCardRequest(employee().getId(), THIS_FRIDAY, 
						8));
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway); //THIS_FRIDAY
		paydayUseCase.execute(new PaydayRequest(THIS_FRIDAY));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(120));
	}
	
	@Test
	public void testPaySingleHourlyEmployeeThreeTimeCardsSpanningThreePayPeriods() throws Exception {
		//GIVEN
		new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 10));
		
		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory).execute(new AddTimeCardRequest(employee().getId(), LAST_FRIDAY, 
						4)); //This is previous pay period, should be ignored
		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory).execute(new AddTimeCardRequest(employee().getId(), THIS_FRIDAY, 
						8)); //This is in this pay period
		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory).execute(new AddTimeCardRequest(employee().getId(), THIS_SATURDAY, 
						4)); //This is next pay period, should be ignored
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
		paydayUseCase.execute(new PaydayRequest(THIS_FRIDAY));
		
		//THEN
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getNetAmount(), is(80));
	}
	
	@Test
	public void testPaySingleSalariedEmployee_UnionMemberAffiliationWeeklyDues_Deducted() throws Exception {
		int weeklyDueAmount = 25;
		
		//GIVEN
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		
		new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
				.execute(new AddUnionMemberAffiliationRequest(employee().getId(), 0, weeklyDueAmount));
		
		
		//WHEN
		LocalDate date = LAST_DAY_OF_A_MONTH_THAT_HAS_4_FRIDAYS;
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
		paydayUseCase.execute(new PaydayRequest(date));
		
		PayCheck payCheck = getSinglePaycheck(paydayUseCase);
		assertThat(payCheck.getDeductionsAmount(), is(4 * 25));
	}
	
	@Test
	public void testPaySingleSalariedEmployee_UnionMemberAffiliationOneServiceCharge_Deducted() throws Exception {
		int unionMemberId = 7005;
		LocalDate date = LAST_DAY_OF_A_MONTH;
		
		//GIVEN
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, 0));
		new AddServiceChargeUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory)
			.execute(new AddServiceChargeRequest(unionMemberId, date, 25));
		
		//SERVICECHARGES
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
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
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, 0));
		new AddServiceChargeUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory)
			.execute(new AddServiceChargeRequest(unionMemberId, dateInPayPeriod, 25));
		new AddServiceChargeUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory)
			.execute(new AddServiceChargeRequest(unionMemberId, dateNotInPayPeriod, 25));
		
		//SERVICECHARGES
		
		//WHEN
		PaydayUseCase paydayUseCase = new PaydayUseCase(transactionalRunner, employeeGateway);
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
		Employee employee = entityFactory.employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}
	
	
}
