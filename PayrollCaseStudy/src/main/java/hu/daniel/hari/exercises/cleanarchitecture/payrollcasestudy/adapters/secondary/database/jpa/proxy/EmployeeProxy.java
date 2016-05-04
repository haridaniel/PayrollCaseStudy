package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.JPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentschedule.JPAPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.affiliation.AffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification.PaymentTypeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentmethod.PaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentschedule.PaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;

/**
 * Violates LSP by downcasting in the setter methods?
 */
@AutoBindedProxy(JPAEmployee.class)
public class EmployeeProxy extends Employee implements Proxy<JPAEmployee> {

	private JPAEmployee jpaEmployee;
	
	private PaymentTypeProxy paymentTypeProxy;
	private PaymentMethodProxy paymentMethodProxy;
	private PaymentScheduleProxy paymentScheduleProxy;
	private AffiliationProxy affiliationProxy;

	@Inject private ProxyFactory proxyFactory;
	@Inject private EntityManager em;
	
	private OneToOneRelationsUpdater oneToOneRelationsUpdater = new OneToOneRelationsUpdater();
	
	@Inject
	public EmployeeProxy(JPAEmployee jpaEmployee) {
		this.jpaEmployee = jpaEmployee;
	}
	
	@Inject
	public void inited() {
		System.err.println("EmployeeProxy + " + em);
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
		return (PaymentMethod) paymentMethodProxy;
	}
	
	@Override
	public PaymentType getPaymentType() {
		if(paymentTypeProxy == null)
			paymentTypeProxy = proxyFactory.create(PaymentTypeProxy.class, jpaEmployee.getJpaPaymentType());
		return (PaymentType) paymentTypeProxy;
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
		oneToOneRelationsUpdater.update(paymentMethodProxy.getJPAObject()); 
	}

	@Override
	public void setPaymentType(PaymentType paymentType) {
		this.paymentTypeProxy = (PaymentTypeProxy) paymentType;
		oneToOneRelationsUpdater.update(paymentTypeProxy.getJPAObject());
	}


	@Override
	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		this.paymentScheduleProxy = (PaymentScheduleProxy) paymentSchedule;
		oneToOneRelationsUpdater.update(paymentScheduleProxy.getJPAObject());
	}
	
	@Override
	public void setAffiliation(Affiliation affiliation) {
		this.affiliationProxy = (AffiliationProxy) affiliation;
		oneToOneRelationsUpdater.update(affiliationProxy.getJPAObject());
	}

	private class OneToOneRelationsUpdater {
		
		public void update(JPAAffiliation newValue) {
			removeOldIfChanged(jpaEmployee.getJpaAffiliation(), newValue, () -> jpaEmployee.setJpaAffiliation(null));
			jpaEmployee.setJpaAffiliation(newValue);
			newValue.connect(jpaEmployee);
		}

		public void update(JPAPaymentSchedule newValue) {
			removeOldIfChanged(jpaEmployee.getJpaPaymentSchedule(), newValue, () -> jpaEmployee.setJpaPaymentSchedule(null));
			jpaEmployee.setJpaPaymentSchedule(newValue);
			newValue.connect(jpaEmployee);
		}

		public void update(JPAPaymentMethod newValue) {
			removeOldIfChanged(jpaEmployee.getJpaPaymentMethod(), newValue, () -> jpaEmployee.setJpaPaymentMethod(null));
			jpaEmployee.setJpaPaymentMethod(newValue);
			newValue.connect(jpaEmployee);
		}

		public void update(JPAPaymentType newValue) {
			removeOldIfChanged(jpaEmployee.getJpaPaymentType(), newValue, () -> jpaEmployee.setJpaPaymentType(null));
			jpaEmployee.setJpaPaymentType(newValue);
			newValue.connect(jpaEmployee);
		}
		
		private <T> void removeOldIfChanged(T oldValue, T newValue, Runnable removeFromJPAEmployee) {
			boolean isChanged = oldValue != null && newValue != oldValue;
			if(isChanged) {
				removeFromJPAEmployee.run();
//				em.remove(oldValue);
				em.remove(em.contains(oldValue) ? oldValue : em.merge(oldValue));
				em.flush();
			}
		}
	}

	@Override
	public JPAEmployee getJPAObject() {
		return jpaEmployee;
	}

}
