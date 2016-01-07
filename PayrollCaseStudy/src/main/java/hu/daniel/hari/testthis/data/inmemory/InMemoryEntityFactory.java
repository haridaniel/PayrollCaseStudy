package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.BiWeeklyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.CommissionedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.DirectPaymentMethodImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.EmployeeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.HoldPaymentMethodImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.HourlyPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.NoAffiliationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.SalariedPaymentClassificationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.SalesReceiptImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.ServiceChargeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.TimeCardImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.UnionMemberAffiliationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.entity.WeeklyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;

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
	public PaymentMethod directPaymentMethod(String accountNumber) {
		return new DirectPaymentMethodImpl(accountNumber);
	}
	@Override
	public MonthlyPaymentSchedule monthlyPaymentSchedule() {
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
