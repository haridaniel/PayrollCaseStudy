package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddSalesReceiptRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.change.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.change.ChangeEmployeeRemoveUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.change.ChangeEmployeeAddUnionMemberAffiliationUseCase;

public class UseCasesITTest extends AbstractDatabaseITTest {

	public UseCasesITTest(PayrollDatabase payrollDatabase) {
		super(payrollDatabase);
	}

	@After
	public void clearDatabase() {
		EntityTransaction transaction = database.getTransaction();
		database.clearDatabase();
		transaction.commit();
	}

	@Test
	public void testAddSalariedEmployeeUseCase() throws Exception {
		new AddSalariedEmployeeUseCase(database, 1, "Bob", "Home", 150_000).execute();

		Employee employee = database.getEmployee(1);
		
		assertEmployeeDefaultFieldsAfterAddEmployee(employee);
		assertEmployee(employee, "Bob", SalariedPaymentClassification.class, MontlhyPaymentSchedule.class);
		assertThat(((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary(), is(150_000));
	}


	@Test
	public void testAddHourlyEmployeeUseCase() throws Exception {
		new AddHourlyEmployeeUseCase(database, 1, "Bob", "Home", 100).execute();
		
		Employee employee = database.getEmployee(1);
		
		assertEmployeeDefaultFieldsAfterAddEmployee(employee);
		assertEmployee(employee, "Bob", HourlyPaymentClassification.class, WeeklyPaymentSchedule.class);
		assertThat(((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage(), is(100));
	}

	@Test
	public void testAddCommissionedEmployeeUseCase() throws Exception {
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.1d;
		new AddCommissionedEmployeeUseCase(database, 1, "Bob", "Home", biWeeklyBaseSalary, commissionRate).execute();
		
		Employee employee = database.getEmployee(1);
		assertEmployeeDefaultFieldsAfterAddEmployee(employee);
		assertEmployee(employee, "Bob", CommissionedPaymentClassification.class, BiWeeklyPaymentSchedule.class);
		CommissionedPaymentClassification commissionedPaymentClassification = (CommissionedPaymentClassification) employee.getPaymentClassification();
		assertThat(commissionedPaymentClassification.getBiWeeklyBaseSalary(), is(70_000));
		assertThat(commissionedPaymentClassification.getCommissionRate(), is(0.1d));
	}
	
	@Test
	public void testAddTimeCardUseCase() throws Exception {
		new AddHourlyEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 115)
			.execute();
		
		LocalDate timecardDate = LocalDate.of(2015, 11, 01);
		
		new AddTimeCardUseCase(database,
				new AddTimeCardRequestModel(employee().getId(), timecardDate, 8))
				.execute();
		
		Employee employee = database.getEmployee(employee().getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification())
				.getTimeCardsIn(DateInterval.of(timecardDate, timecardDate)));
		assertEquals(8, timeCard.getWorkingHourQty());
	}

	@Test
	public void testAddSalesReceiptUseCase() throws Exception {
		new AddCommissionedEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 0, (double) 0).execute();
		LocalDate salesReceiptDate = LocalDate.of(2015, 11, 01);
		new AddSalesReceiptUseCase(database, new AddSalesReceiptRequestModel(employee().getId(), salesReceiptDate, 25000)).execute();
		
		Employee employee = database.getEmployee(employee().getId());
		SalesReceipt salesReceipt = singleResult(((CommissionedPaymentClassification) employee.getPaymentClassification())
				.getSalesReceiptsIn(DateInterval.of(salesReceiptDate, salesReceiptDate)));
		assertThat(salesReceipt.getAmount(), is(25000));
		System.out.println();
	}
	
	
	@Test
	public void testDeleteEmployeeUseCase() throws Exception {
		database.addEmployee(employee());
		
		new DeleteEmployeeUseCase(database, employee().getId()).execute();
		
		assertNull(database.getEmployee(employee().getId()));
	}
	
	@Test
	public void testChangeEmployeeNameUseCase() throws Exception {
		new AddSalariedEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 1005)
			.execute();
		
		new ChangeEmployeeNameUseCase(database, employee().getId(), "Janos")
			.execute();
		
		Employee employee = database.getEmployee(employee().getId());
		assertEquals("Janos", employee.getName());
	}

	@Test
	public void testChangeEmployeeAddUnionMemberAffiliationUseCase() throws Exception {
		int unionMemberId = 7150;
		int weeklyDueAmount = 25;
		
		new AddSalariedEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 1005)
			.execute();
		new ChangeEmployeeAddUnionMemberAffiliationUseCase(database, employee().getId(), unionMemberId, weeklyDueAmount)
			.execute();
		
		Employee employee = database.getEmployee(employee().getId());
		assertThat(employee.getAffiliation(), instanceOf(UnionMemberAffiliation.class));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getUnionMemberId(), is(unionMemberId));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getWeeklyDueAmount(), is(weeklyDueAmount));
		
	}
	
	@Test
	public void testChangeEmployeeRemoveUnionMemberAffiliationUseCase() throws Exception {
		int unionMemberId = 7150;
		
		new AddSalariedEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 1005)
			.execute();
		new ChangeEmployeeAddUnionMemberAffiliationUseCase(database, employee().getId(), unionMemberId, 0)
			.execute();
		new ChangeEmployeeRemoveUnionMemberAffiliationUseCase(database, unionMemberId)
			.execute();
		
		Employee employee = database.getEmployee(employee().getId());
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
		Employee employee = database.factory().employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}

	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}
	
	
}
