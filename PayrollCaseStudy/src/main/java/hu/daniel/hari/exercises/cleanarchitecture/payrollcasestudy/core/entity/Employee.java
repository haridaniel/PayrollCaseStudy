package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class Employee {
	public int id;
	private String name;
	public String address;

	private PaymentClassification paymentClassification;
	public PaymentSchedule paymentSchedule;
	public PaymentMethod paymentMethod;
	
	public PaymentClassification getPaymentClassification() {
		return paymentClassification;
	}

	public void setPaymentClassification(PaymentClassification paymentClassification) {
		this.paymentClassification = paymentClassification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
