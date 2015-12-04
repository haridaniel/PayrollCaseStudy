package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification.JPAPaymentClassificationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentmethod.JPAPaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentschedule.JPAPaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentClassificationProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentMethodProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentScheduleProxyFactory;

public class EmployeeProxy extends Employee {

	private JPAEmployee jpaEmployee;

	public EmployeeProxy(JPAEmployee jpaEmployee) {
		this.jpaEmployee = jpaEmployee;
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
		return jpaEmployee.jpaPaymentMethod == null ? null : 
			PaymentMethodProxyFactory.create(jpaEmployee.jpaPaymentMethod);
	}
	
	@Override
	public PaymentClassification getPaymentClassification() {
		return jpaEmployee.jpaPaymentClassification == null? null : 
			PaymentClassificationProxyFactory.create(jpaEmployee.jpaPaymentClassification);
	}
	
	@Override
	public PaymentSchedule getPaymentSchedule() {
		return jpaEmployee.jpaPaymentSchedule == null? null :
			PaymentScheduleProxyFactory.create(jpaEmployee.jpaPaymentSchedule);
	}
	
	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		jpaEmployee.jpaPaymentMethod = JPAPaymentMethodFactory.create(paymentMethod); 
	}
	
	@Override
	public void setPaymentClassification(PaymentClassification paymentClassification) {
		jpaEmployee.jpaPaymentClassification = JPAPaymentClassificationFactory.create(jpaEmployee, paymentClassification);
	}
	
	@Override
	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		jpaEmployee.jpaPaymentSchedule = JPAPaymentScheduleFactory.create(paymentSchedule);
	}
	
}
