package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.RemoveUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway.NoSuchEmployeeException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddSalesReceiptRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;

public class UseCasesITTest extends AbstractDatabaseITTest {

	private static final LocalDate A_DATE = LocalDate.of(2015, 11, 01);
	
	private Database database;
	private EmployeeGateway employeeGateway;
	private TransactionalRunner transactionalRunner;
	private EntityFactory entityFactory;

	public UseCasesITTest(Database database) {
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
	public void testAddSalariedEmployeeUseCase() throws Exception {
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory).execute(new AddSalariedEmployeeRequest(1, "Bob", "Home", 150_000));

		Employee employee = employeeGateway.findById(1);
		
		assertEmployeeDefaultFieldsAfterAddEmployee(employee);
		assertEmployee(employee, "Bob", SalariedPaymentClassification.class, MontlhyPaymentSchedule.class);
		assertThat(((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary(), is(150_000));
	}


	@Test
	public void testAddHourlyEmployeeUseCase() throws Exception {
		new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory).execute(new AddHourlyEmployeeRequest(1, "Bob", "Home", 100));
		
		Employee employee = employeeGateway.findById(1);
		
		assertEmployeeDefaultFieldsAfterAddEmployee(employee);
		assertEmployee(employee, "Bob", HourlyPaymentClassification.class, WeeklyPaymentSchedule.class);
		assertThat(((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage(), is(100));
	}

	@Test
	public void testAddTimeCardUseCase() throws Exception {
		new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddHourlyEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 115));
		
		LocalDate timecardDate = A_DATE;
		
		new AddTimeCardUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory)
				.execute(new AddTimeCardRequest(employee().getId(), timecardDate, 8));
		
		Employee employee = employeeGateway.findById(employee().getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification())
				.getTimeCardsIn(DateInterval.of(timecardDate, timecardDate)));
		assertEquals(8, timeCard.getWorkingHourQty());
	}

	@Test
	public void testAddCommissionedEmployeeUseCase() throws Exception {
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.1d;
		new AddCommissionedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddCommissionedEmployeeRequest(1, "Bob", "Home", biWeeklyBaseSalary, commissionRate));
		
		Employee employee = employeeGateway.findById(1);
		assertEmployeeDefaultFieldsAfterAddEmployee(employee);
		assertEmployee(employee, "Bob", CommissionedPaymentClassification.class, BiWeeklyPaymentSchedule.class);
		CommissionedPaymentClassification commissionedPaymentClassification = (CommissionedPaymentClassification) employee.getPaymentClassification();
		assertThat(commissionedPaymentClassification.getBiWeeklyBaseSalary(), is(70_000));
		assertThat(commissionedPaymentClassification.getCommissionRate(), is(0.1d));
	}

	@Test
	public void testAddSalesReceiptUseCase() throws Exception {
		new AddCommissionedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddCommissionedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0, (double) 0));
		LocalDate salesReceiptDate = A_DATE;
		new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory).execute(new AddSalesReceiptRequest(employee().getId(), salesReceiptDate, 25000));
		
		Employee employee = employeeGateway.findById(employee().getId());
		SalesReceipt salesReceipt = singleResult(((CommissionedPaymentClassification) employee.getPaymentClassification())
				.getSalesReceiptsIn(DateInterval.of(salesReceiptDate, salesReceiptDate)));
		assertThat(salesReceipt.getAmount(), is(25000));
	}
	
	
	@Test
	public void testAddServiceChargeUseCase() throws Exception {
		int unionMemberId = 7005;
		
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, 0));
		new AddServiceChargeUseCase(database.transactionalRunner(), database.employeeGateway(), entityFactory)
			.execute(new AddServiceChargeRequest(unionMemberId, A_DATE, 25));
		
		Employee employee = employeeGateway.findById(1);
		UnionMemberAffiliation affiliation = (UnionMemberAffiliation) employee.getAffiliation();
		ServiceCharge serviceCharge = singleResult(affiliation.getServiceChargesIn(DateInterval.ofSingleDate(A_DATE)));
		assertThat(serviceCharge.getAmount(), is(25));
		
	}

	@Test(expected = NoSuchEmployeeException.class)
	public void testDeleteEmployeeUseCase() throws Exception {
		employeeGateway.addNew(employee());
		new DeleteEmployeeUseCase(database.transactionalRunner(), database.employeeGateway()).execute(new DeleteEmployeeRequest(employee().getId()));
		employeeGateway.findById(employee().getId());
	}
	
	@Test
	public void testChangeEmployeeNameUseCase() throws Exception {
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 1005));
		
		new ChangeEmployeeNameUseCase(transactionalRunner, employeeGateway)
			.execute(new ChangeEmployeeNameRequest(employee().getId(), "Janos"));
		
		Employee employee = employeeGateway.findById(employee().getId());
		assertEquals("Janos", employee.getName());
	}

	@Test
	public void testChangeEmployeeAddUnionMemberAffiliationUseCase() throws Exception {
		int unionMemberId = 7150;
		int weeklyDueAmount = 25;
		
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 0));
		new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, weeklyDueAmount));
		
		Employee employee = employeeGateway.findById(employee().getId());
		assertThat(employee.getAffiliation(), instanceOf(UnionMemberAffiliation.class));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getUnionMemberId(), is(unionMemberId));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getWeeklyDueAmount(), is(weeklyDueAmount));
		
	}
	
	@Test
	public void testChangeEmployeeRemoveUnionMemberAffiliationUseCase() throws Exception {
		int unionMemberId = 7150;
		
		new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory)
			.execute(new AddSalariedEmployeeRequest(employee().getId(), employee().getName(), employee().getAddress(), 1005));
		new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
			.execute(new AddUnionMemberAffiliationRequest(employee().getId(), unionMemberId, 0));
		new RemoveUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory)
			.execute(new RemoveUnionMemberAffiliationRequest(unionMemberId));
		
		Employee employee = employeeGateway.findById(employee().getId());
		assertThat(employee.getAffiliation(), instanceOf(NoAffiliation.class));
	}
	
	private void assertEmployeeDefaultFieldsAfterAddEmployee(Employee employee) {
		assertThat(employee.getPaymentMethod(), instanceOf(HoldPaymentMethod.class));
		assertThat(employee.getAffiliation(), instanceOf(NoAffiliation.class));
	}
	
	private void assertEmployee(Employee employee, String name,	Class<? extends PaymentClassification> paymentClassification, Class<? extends PaymentSchedule> paymentSchedule) {
		assertNotNull(employee);
		assertEquals(employee.getName(), name);
		assertThat(employee.getPaymentClassification(), instanceOf(paymentClassification));
		assertThat(employee.getPaymentSchedule(), instanceOf(paymentSchedule));
	}

	private Employee employee() {
		Employee employee = entityFactory.employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}

	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}
	
	
}
