package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;

public interface EntityFactory {

	Employee employee();
	
	SalariedPaymentClassification salariedPaymentClassification(int monthlySalary);
	HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage);
	CommissionedPaymentClassification commissionedPaymentClassification(int biWeeklyBaseSalary, double commissionRate);
	
	PaymentMethod holdPaymentMethod();
	MontlhyPaymentSchedule monthlyPaymentSchedule();
	WeeklyPaymentSchedule weeklyPaymentSchedule();
	BiWeeklyPaymentSchedule biWeeklyPaymentSchedule();
	
	UnionMemberAffiliation unionMemberAffiliation(int unionMemberId, int weeklyDueAmount);
	
	TimeCard timeCard(LocalDate date, int workingHoursQty);
	SalesReceipt salesReceipt(LocalDate date, int amount);
	
}
