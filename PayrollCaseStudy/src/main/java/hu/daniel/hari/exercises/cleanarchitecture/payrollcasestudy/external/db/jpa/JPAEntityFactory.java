package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy.EmployeeProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.CommissionedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy.HourlyPaymentClassificationProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.commissioned.SalesReceiptProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.hourly.TimeCardProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.HoldPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.BiWeeklyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.MonthlyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.WeeklyPaymentScheduleProxy;

@Singleton
public class JPAEntityFactory implements EntityFactory {
	
	@Inject private EmployeeProxyFactory employeeProxyFactory;
	@Inject private HourlyPaymentClassificationProxyFactory hourlyPaymentClassificationProxyFactory;
	
	@Override
	public Employee employee() {
		return employeeProxyFactory.create(new JPAEmployee());
	}

	@Override
	public SalariedPaymentClassification salariedPaymentClassification(int monthlySalary) {
		return new SalariedPaymentClassificationProxy(new SalariedJPAPaymentClassification(monthlySalary));
	}

	@Override
	public HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage) {
		return hourlyPaymentClassificationProxyFactory.create(new HourlyJPAPaymentClassification(hourlyWage));
	}

	@Override
	public CommissionedPaymentClassification commissionedPaymentClassification(int biWeeklyBaseSalary, double commissionRate) {
		return new CommissionedPaymentClassificationProxy(new CommissionedJPAPaymentClassification(biWeeklyBaseSalary, commissionRate));
	}

	@Override
	public HoldPaymentMethod holdPaymentMethod() {
		return new HoldPaymentMethodProxy();
	}

	@Override
	public MontlhyPaymentSchedule monthlyPaymentSchedule() {
		return new MonthlyPaymentScheduleProxy();
	}

	@Override
	public WeeklyPaymentSchedule weeklyPaymentSchedule() {
		return new WeeklyPaymentScheduleProxy();
	}

	@Override
	public BiWeeklyPaymentSchedule biWeeklyPaymentSchedule() {
		return new BiWeeklyPaymentScheduleProxy();
	}

	@Override
	public TimeCard timeCard(LocalDate date, int workingHoursQty) {
		return new TimeCardProxy(new JPATimeCard(date, workingHoursQty));
	}

	@Override
	public SalesReceipt salesReceipt(LocalDate date, int amount) {
		return new SalesReceiptProxy(new JPASalesReceipt(date, amount));
	}

	@Override
	public UnionMemberAffiliation unionMemberAffiliation(int unionMemberId, int weeklyDueAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceCharge serviceCharge(LocalDate date, int amount) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
