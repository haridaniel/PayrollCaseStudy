package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.PaymentType;

public class EmployeeImpl extends Employee {
	private int id;
	private String name;
	private String address;

	private PaymentType paymentType;
	private PaymentSchedule paymentSchedule;
	private PaymentMethod paymentMethod;
	private Affiliation affiliation;
	
	@Override
	public PaymentType getPaymentType() {
		return paymentType;
	}

	@Override
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
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
