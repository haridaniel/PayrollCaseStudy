package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.PaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.PaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.PaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentClassificationProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentMethodProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentScheduleProxyFactory;

public class EmployeeProxy extends Employee {

	private JPAEmployee jpaEmployee;
	
	private PaymentClassificationProxy paymentClassificationProxy;
	private PaymentMethodProxy paymentMethodProxy;
	private PaymentScheduleProxy paymentScheduleProxy;

	@Inject
	private PaymentClassificationProxyFactory paymentClassificationProxyFactory;
	
	@Inject
	public EmployeeProxy(@Assisted JPAEmployee jpaEmployee) {
		this.jpaEmployee = jpaEmployee;
	}

	public JPAEmployee getJpaEmployee() {
		return jpaEmployee;
	}
	
	@Override
	public int getId() {
		return jpaEmployee.id;
	}

	@Override
	public void setId(int id) {
		jpaEmployee.id = id;
	}

	@Override
	public String getName() {
		return jpaEmployee.name;
	}

	@Override
	public void setName(String name) {
		jpaEmployee.name = name;
	}

	@Override
	public String getAddress() {
		return jpaEmployee.address;
	}

	@Override
	public void setAddress(String address) {
		jpaEmployee.address = address;
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		if(paymentMethodProxy == null)
			paymentMethodProxy = PaymentMethodProxyFactory.create(jpaEmployee.getJpaPaymentMethod());
		return paymentMethodProxy;
	}
	
	@Override
	public PaymentClassification getPaymentClassification() {
		if(paymentClassificationProxy == null)
			paymentClassificationProxy = (PaymentClassificationProxy) paymentClassificationProxyFactory.create(jpaEmployee.getJpaPaymentClassification());
		return (PaymentClassification) paymentClassificationProxy;
	}
	
	@Override
	public PaymentSchedule getPaymentSchedule() {
		if(paymentScheduleProxy == null)
			paymentScheduleProxy = PaymentScheduleProxyFactory.create(jpaEmployee.getJpaPaymentSchedule());
		return paymentScheduleProxy;
	}
	
	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethodProxy = (PaymentMethodProxy) paymentMethod;
		jpaEmployee.setJpaPaymentMethod(paymentMethodProxy.getJPAPaymentMethod()); 
	}

	@Override
	public void setPaymentClassification(PaymentClassification paymentClassification) {
		this.paymentClassificationProxy = (PaymentClassificationProxy) paymentClassification;
		jpaEmployee.setJpaPaymentClassification(paymentClassificationProxy.getJPAPaymentClassification());
	}

	@Override
	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		this.paymentScheduleProxy = (PaymentScheduleProxy) paymentSchedule;
		jpaEmployee.setJpaPaymentSchedule(paymentScheduleProxy.getJPAPaymentSchedule());
	}
	
	public interface EmployeeProxyFactory {
		public EmployeeProxy create(JPAEmployee jpaEmployee);
	}
	
}
