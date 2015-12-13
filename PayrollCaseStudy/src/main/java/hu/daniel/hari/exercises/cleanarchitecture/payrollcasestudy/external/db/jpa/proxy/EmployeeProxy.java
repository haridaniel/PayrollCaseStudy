package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.AffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.PaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.PaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.PaymentScheduleProxy;

/**
 * Violates LSP
 */
public class EmployeeProxy extends Employee {

	private JPAEmployee jpaEmployee;
	
	private PaymentClassificationProxy paymentClassificationProxy;
	private PaymentMethodProxy paymentMethodProxy;
	private PaymentScheduleProxy paymentScheduleProxy;
	private AffiliationProxy affiliationProxy;

	@Inject private ProxyFactory proxyFactory;
	@Inject private EntityManager em;
	
	private JPAUpdater updater = new JPAUpdater();
	
	@Inject
	public EmployeeProxy(JPAEmployee jpaEmployee) {
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
			paymentMethodProxy = proxyFactory.create(PaymentMethodProxy.class, jpaEmployee.getJpaPaymentMethod());
		return paymentMethodProxy;
	}
	
	@Override
	public PaymentClassification getPaymentClassification() {
		if(paymentClassificationProxy == null)
			paymentClassificationProxy = proxyFactory.create(PaymentClassificationProxy.class, jpaEmployee.getJpaPaymentClassification());
		return (PaymentClassification) paymentClassificationProxy;
	}
	
	@Override
	public PaymentSchedule getPaymentSchedule() {
		if(paymentScheduleProxy == null)
			paymentScheduleProxy = proxyFactory.create(PaymentScheduleProxy.class, jpaEmployee.getJpaPaymentSchedule());
		return (PaymentSchedule) paymentScheduleProxy;
	}
	
	@Override
	public Affiliation getAffiliation() {
		if (affiliationProxy == null)
			affiliationProxy = proxyFactory.create(AffiliationProxy.class, jpaEmployee.getJpaAffiliation());
		return (Affiliation) affiliationProxy;
	}

	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethodProxy = (PaymentMethodProxy) paymentMethod;
		jpaEmployee.setJpaPaymentMethod(paymentMethodProxy.getJPAPaymentMethod()); 
	}

	@Override
	public void setPaymentClassification(PaymentClassification paymentClassification) {
		this.paymentClassificationProxy = (PaymentClassificationProxy) paymentClassification;
		updater.update(paymentClassificationProxy.getJPAPaymentClassification());
	}


	@Override
	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		this.paymentScheduleProxy = (PaymentScheduleProxy) paymentSchedule;
		jpaEmployee.setJpaPaymentSchedule(paymentScheduleProxy.getJPAPaymentSchedule());
	}
	
	@Override
	public void setAffiliation(Affiliation affiliation) {
		this.affiliationProxy = (AffiliationProxy) affiliation;
		updater.update(affiliationProxy.getJpaAffiliation());
	}

	private class JPAUpdater {
		
		private void update(JPAAffiliation newValue) {
			removeOldIfChanged(jpaEmployee.getJpaAffiliation(), newValue, () -> jpaEmployee.setJpaAffiliation(null));
			jpaEmployee.setJpaAffiliation(newValue);
			newValue.connect(jpaEmployee);
		}

		private void update(JPAPaymentClassification newValue) {
			removeOldIfChanged(jpaEmployee.getJpaPaymentClassification(), newValue, () -> jpaEmployee.setJpaPaymentClassification(null));
			jpaEmployee.setJpaPaymentClassification(newValue);
			newValue.connect(jpaEmployee);
		}
		
		private <T> void removeOldIfChanged(T oldValue, T newValue, Runnable removeFromJPAEmployee) {
			boolean isChanged = oldValue != null && newValue != oldValue;
			if(isChanged) {
				removeFromJPAEmployee.run();
				em.remove(oldValue);
				em.flush();
			}
		}
	}
	
}
