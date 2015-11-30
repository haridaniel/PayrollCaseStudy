package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class Employee {
	public int id;
	public String name;
	public String address;

	public PaymentClassification paymentClassification;
	public PaymentSchedule paymentSchedule;
	public PaymentMethod paymentMethod; 
	
}
