package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public interface EntityFactory {

	Employee employee();
	SalariedPaymentClassification salariedPaymentClassification(int monthlySalary);
	HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage);
	PaymentMethod holdPaymentMethod();
	PaymentSchedule monthlyPaymentSchedule();
	PaymentSchedule weeklyPaymentSchedule();
	TimeCard timeCard(LocalDate date, int workingHoursQty);
	
}
