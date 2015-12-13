package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.CommissionedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.PaymentScheduleProxy;

@Singleton
public class ProxyFactory {

	private ProxyAssignmentConfiguration proxyAssignmentConfiguration;
	
	@Inject
	public ProxyFactory(ProxyAssignmentConfiguration proxyAssignmentConfiguration) {
		this.proxyAssignmentConfiguration = proxyAssignmentConfiguration;
	}
	
	public <T> T create(Class<T> proxyClass, Object jpaEntity) {
		Object proxy = createProxyFor(jpaEntity);
		JPAPayrollDatabaseModule.INJECTOR.injectMembers(proxy);
		return (T) proxy;
	}

	private Object createProxyFor(Object jpaEntity) {
		return newInstanceWithParameter(proxyAssignmentConfiguration.getProxyClassFor(jpaEntity), jpaEntity);
	}
		
	private <T> T newInstanceWithParameter(Class<T> type, Object parameter) {
		try {
			Constructor<T> constructor = type.getDeclaredConstructor(parameter.getClass());
			return (T) constructor.newInstance(parameter);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
