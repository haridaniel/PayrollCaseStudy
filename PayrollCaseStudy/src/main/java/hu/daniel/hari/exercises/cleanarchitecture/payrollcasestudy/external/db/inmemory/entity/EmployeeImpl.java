package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public class EmployeeImpl extends Employee {
	private int id;
	private String name;
	private String address;

	private PaymentClassification paymentClassification;
	private PaymentSchedule paymentSchedule;
	private PaymentMethod paymentMethod;
	private Affiliation affiliation;
	
	@Override
	public PaymentClassification getPaymentClassification() {
		return paymentClassification;
	}

	@Override
	public void setPaymentClassification(PaymentClassification paymentClassification) {
		this.paymentClassification = paymentClassification;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public PaymentSchedule getPaymentSchedule() {
		return paymentSchedule;
	}

	@Override
	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	@Override
	public Affiliation getAffiliation() {
		return affiliation;
	}
	
	@Override
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
}
