package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa;

import java.time.LocalDate;

import javax.inject.Inject;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation.JPANoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentmethod.JPADirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentmethod.JPAHoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentschedule.JPABiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentschedule.JPAMonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentschedule.JPAWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.affiliation.NoAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.affiliation.unionmember.ServiceChargeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.affiliation.unionmember.UnionMemberAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentclassification.CommissionedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentclassification.commissioned.SalesReceiptProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentclassification.hourly.TimeCardProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentmethod.DirectPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentmethod.HoldPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentschedule.BiWeeklyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentschedule.MonthlyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentschedule.WeeklyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalesReceipt.SalesReceiptFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;

@Singleton
public class JPAEntityFactory implements EntityFactory {
	
	@Inject private ProxyFactory proxyFactory;

	@Override
	public Employee employee() {
		return proxyFactory.create(EmployeeProxy.class, new JPAEmployee());
	}

	@Override
	public SalariedPaymentClassification salariedPaymentClassification(int monthlySalary) {
		return proxyFactory.create(SalariedPaymentClassificationProxy.class, new SalariedJPAPaymentClassification(monthlySalary));
	}

	@Override
	public HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage) {
		return proxyFactory.create(HourlyPaymentClassificationProxy.class, new HourlyJPAPaymentClassification(hourlyWage));
	}

	@Override
	public CommissionedPaymentClassification commissionedPaymentClassification(int biWeeklyBaseSalary, double commissionRate) {
		return proxyFactory.create(CommissionedPaymentClassificationProxy.class, new CommissionedJPAPaymentClassification(biWeeklyBaseSalary, commissionRate));
	}

	@Override
	public HoldPaymentMethod holdPaymentMethod() {
		return proxyFactory.create(HoldPaymentMethodProxy.class, new JPAHoldPaymentMethod());
	}

	@Override
	public PaymentMethod directPaymentMethod(String accountNumber) {
		return proxyFactory.create(DirectPaymentMethodProxy.class, new JPADirectPaymentMethod(accountNumber));
	}

	@Override
	public MonthlyPaymentSchedule monthlyPaymentSchedule() {
		return proxyFactory.create(MonthlyPaymentScheduleProxy.class, new JPAMonthlyPaymentSchedule());
	}

	@Override
	public WeeklyPaymentSchedule weeklyPaymentSchedule() {
		return proxyFactory.create(WeeklyPaymentScheduleProxy.class, new JPAWeeklyPaymentSchedule());
	}

	@Override
	public BiWeeklyPaymentSchedule biWeeklyPaymentSchedule() {
		return proxyFactory.create(BiWeeklyPaymentScheduleProxy.class, new JPABiWeeklyPaymentSchedule());
	}

	@Override
	public TimeCard timeCard(LocalDate date, int workingHoursQty) {
		return proxyFactory.create(TimeCardProxy.class, new JPATimeCard(date, workingHoursQty));
	}

	@Override
	public SalesReceipt salesReceipt(LocalDate date, int amount) {
		return proxyFactory.create(SalesReceiptProxy.class, new JPASalesReceipt(date, amount));
	}

	@Override
	public NoAffiliation noAffiliation() {
		return proxyFactory.create(NoAffiliationProxy.class, new JPANoAffiliation());
	}

	@Override
	public UnionMemberAffiliation unionMemberAffiliation(int unionMemberId, int weeklyDueAmount) {
		return proxyFactory.create(UnionMemberAffiliationProxy.class, new JPAUnionMemberAffiliation(unionMemberId, weeklyDueAmount));
	}

	@Override
	public ServiceCharge serviceCharge(LocalDate date, int amount) {
		return proxyFactory.create(ServiceChargeProxy.class, new JPAServiceCharge(date, amount));
	}
	
}
