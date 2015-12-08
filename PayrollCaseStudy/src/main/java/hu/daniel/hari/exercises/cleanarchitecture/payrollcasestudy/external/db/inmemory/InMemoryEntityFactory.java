package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

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
	public PaymentSchedule monthlyPaymentSchedule() {
		return new MonthlyPaymentScheduleImpl();
	}

	@Override
	public PaymentSchedule weeklyPaymentSchedule() {
		return new WeeklyPaymentScheduleImpl();
	}

	@Override
	public PaymentSchedule biWeeklyPaymentSchedule() {
		return new BiWeeklyPaymentScheduleImpl();
	}

	@Override
	public TimeCard timeCard(LocalDate date, int workingHoursQty) {
		return new TimeCardImpl(date, workingHoursQty);
	}
	
}
