package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentClassificationProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentMethodProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory.PaymentScheduleProxyFactory;

public class EmployeeProxy extends Employee {

	private JPAEmployee jPAEmployee;

	public EmployeeProxy(JPAEmployee jpaEmployee) {
		this.jPAEmployee = jpaEmployee;
	}

	@Override
	public int getId() {
		return jPAEmployee.id;
	}

	@Override
	public void setId(int id) {
		jPAEmployee.id = id;
	}

	@Override
	public String getName() {
		return jPAEmployee.name;
	}

	@Override
	public void setName(String name) {
		jPAEmployee.name = name;
	}

	@Override
	public String getAddress() {
		return jPAEmployee.address;
	}

	@Override
	public void setAddress(String address) {
		jPAEmployee.address = address;
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		return jPAEmployee.jpaPaymentMethod == null ? null : 
			PaymentMethodProxyFactory.create(jPAEmployee.jpaPaymentMethod);
	}
	
	@Override
	public PaymentClassification getPaymentClassification() {
		return jPAEmployee.jpaPaymentSize == null? null : 
			PaymentClassificationProxyFactory.create(jPAEmployee.jpaPaymentSize);
	}
	
	@Override
	public PaymentSchedule getPaymentSchedule() {
		return jPAEmployee.jpaPaymentSchedule == null? null :
			PaymentScheduleProxyFactory.create(jPAEmployee.jpaPaymentSchedule);
	}
	
}
