package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy.EmployeeProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy.HourlyPaymentClassificationProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.hourly.TimeCardProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.HoldPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.MonthlyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.WeeklyPaymentScheduleProxy;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JPAEntityFactory implements EntityFactory {
	
	@Inject private EmployeeProxyFactory employeeProxyFactory;
	@Inject private HourlyPaymentClassificationProxyFactory hourlyPaymentClassificationProxyFactory;
	
	@Override
	public SalariedPaymentClassification salariedPaymentClassification(int monthlySalary) {
		return new SalariedPaymentClassificationProxy(new SalariedJPAPaymentClassification(monthlySalary));
	}

	@Override
	public HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage) {
		return hourlyPaymentClassificationProxyFactory.create(new HourlyJPAPaymentClassification(hourlyWage));
	}

	@Override
	public Employee employee() {
		return employeeProxyFactory.create(new JPAEmployee());
	}

	@Override
	public PaymentMethod holdPaymentMethod() {
		return new HoldPaymentMethodProxy();
	}

	@Override
	public PaymentSchedule monthlyPaymentSchedule() {
		return new MonthlyPaymentScheduleProxy();
	}

	@Override
	public PaymentSchedule weeklyPaymentSchedule() {
		return new WeeklyPaymentScheduleProxy();
	}

	@Override
	public TimeCard timeCard(LocalDate date, int workingHoursQty) {
		return new TimeCardProxy(new JPATimeCard(date, workingHoursQty));
	}
	
}
