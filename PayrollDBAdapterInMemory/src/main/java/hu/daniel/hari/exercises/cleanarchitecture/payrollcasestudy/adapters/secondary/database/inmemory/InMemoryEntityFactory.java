package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.BiWeeklyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.CommissionedPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.DirectPaymentMethodImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.EmployeeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.PaymasterPaymentMethodImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.HourlyPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.MonthlyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.NoAffiliationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalariedPaymentTypeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.SalesReceiptImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.ServiceChargeImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.TimeCardImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.UnionMemberAffiliationImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity.WeeklyPaymentScheduleImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;

public class InMemoryEntityFactory implements EntityFactory {

	@Override
	public SalariedPaymentType salariedPaymentType(int monthlySalary) {
		return new SalariedPaymentTypeImpl(monthlySalary);
	}

	@Override
	public HourlyPaymentType hourlyPaymentType(int hourlyWage) {
		return new HourlyPaymentTypeImpl(hourlyWage);
	}

	@Override
	public CommissionedPaymentType commissionedPaymentType(int biWeeklyBaseSalary, double commissionRate) {
		return new CommissionedPaymentTypeImpl(biWeeklyBaseSalary, commissionRate);
	}

	@Override
	public Employee employee() {
		return new EmployeeImpl();
	}

	@Override
	public PaymentMethod paymasterPaymentMethod() {
		return new PaymasterPaymentMethodImpl();
	}

	@Override
	public PaymentMethod directPaymentMethod(String accountNumber) {
		return new DirectPaymentMethodImpl(accountNumber);
	}
	@Override
	public PaymentSchedule monthlyPaymentSchedule() {
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
