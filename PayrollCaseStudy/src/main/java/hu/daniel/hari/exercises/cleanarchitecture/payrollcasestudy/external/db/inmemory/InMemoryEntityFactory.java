package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.BiWeeklyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.CommissionedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.EmployeeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.HoldPaymentMethodImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.HourlyPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.NoAffiliationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.SalariedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.SalesReceiptImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.ServiceChargeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.TimeCardImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.UnionMemberAffiliationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity.WeeklyPaymentScheduleImpl;

public class InMemoryEntityFactory implements EntityFactory {

	@Override
	public SalariedPaymentClassification salariedPaymentClassification(int monthlySalary) {
		return new SalariedPaymentClassificationImpl(monthlySalary);
	}

	@Override
	public HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage) {
		return new HourlyPaymentClassificationImpl(hourlyWage);
	}

	@Override
	public CommissionedPaymentClassification commissionedPaymentClassification(int biWeeklyBaseSalary, double commissionRate) {
		return new CommissionedPaymentClassificationImpl(biWeeklyBaseSalary, commissionRate);
	}

	@Override
	public Employee employee() {
		return new EmployeeImpl();
	}

	@Override
	public PaymentMethod holdPaymentMethod() {
		return new HoldPaymentMethodImpl();
	}

	@Override
	public MontlhyPaymentSchedule monthlyPaymentSchedule() {
		return new MonthlyPaymentScheduleImpl();
	}

	@Override
	public WeeklyPaymentSchedule weeklyPaymentSchedule() {
		return new WeeklyPaymentScheduleImpl();
	}

	@Override
	public BiWeeklyPaymentSchedule biWeeklyPaymentSchedule() {
		return new BiWeeklyPaymentScheduleImpl();
	}

	@Override
	public TimeCard timeCard(LocalDate date, int workingHoursQty) {
		return new TimeCardImpl(date, workingHoursQty);
	}

	@Override
	public SalesReceipt salesReceipt(LocalDate date, int amount) {
		return new SalesReceiptImpl(date, amount);
	}

	@Override
	public NoAffiliation noAffiliation() {
		return new NoAffiliationImpl();
	}
	
	@Override
	public UnionMemberAffiliation unionMemberAffiliation(int unionMemberId, int weeklyDueAmount) {
		return new UnionMemberAffiliationImpl(unionMemberId, weeklyDueAmount);
	}

	@Override
	public ServiceCharge serviceCharge(LocalDate date, int amount) {
		return new ServiceChargeImpl(date, amount);
	}

	
}
