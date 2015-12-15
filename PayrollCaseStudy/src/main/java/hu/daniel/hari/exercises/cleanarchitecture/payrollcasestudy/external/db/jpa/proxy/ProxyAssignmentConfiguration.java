package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPANoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentmethod.JPAHoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentschedule.JPABiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentschedule.JPAMonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentschedule.JPAWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.NoAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember.ServiceChargeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember.UnionMemberAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.CommissionedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.commissioned.SalesReceiptProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.hourly.TimeCardProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.HoldPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.BiWeeklyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.MonthlyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.WeeklyPaymentScheduleProxy;

@Singleton
public class ProxyAssignmentConfiguration {
	
	private Map<Class<?>, Class<?>> proxyClassesByJPAEntityClass = new HashMap<>();
	{
		proxyClassesByJPAEntityClass.put(JPAEmployee.class, EmployeeProxy.class);
		proxyClassesByJPAEntityClass.put(SalariedJPAPaymentClassification.class, SalariedPaymentClassificationProxy.class);
		proxyClassesByJPAEntityClass.put(HourlyJPAPaymentClassification.class, HourlyPaymentClassificationProxy.class);
		proxyClassesByJPAEntityClass.put(CommissionedJPAPaymentClassification.class, CommissionedPaymentClassificationProxy.class);
		proxyClassesByJPAEntityClass.put(JPANoAffiliation.class, NoAffiliationProxy.class);
		proxyClassesByJPAEntityClass.put(JPAUnionMemberAffiliation.class, UnionMemberAffiliationProxy.class);
		proxyClassesByJPAEntityClass.put(JPATimeCard.class, TimeCardProxy.class);
		proxyClassesByJPAEntityClass.put(JPASalesReceipt.class, SalesReceiptProxy.class);
		proxyClassesByJPAEntityClass.put(JPAServiceCharge.class, ServiceChargeProxy.class);
		
		proxyClassesByJPAEntityClass.put(JPAHoldPaymentMethod.class, HoldPaymentMethodProxy.class);
		
		proxyClassesByJPAEntityClass.put(JPAMonthlyPaymentSchedule.class, MonthlyPaymentScheduleProxy.class);
		proxyClassesByJPAEntityClass.put(JPAWeeklyPaymentSchedule.class, WeeklyPaymentScheduleProxy.class);
		proxyClassesByJPAEntityClass.put(JPABiWeeklyPaymentSchedule.class, BiWeeklyPaymentScheduleProxy.class);
		
//		proxyClassesByJPAEntityClass.put(.class, .class);
		
	}
	
	private Map<Object, Class<?>> proxyClassesByJPAEntity = new HashMap<>();
	{
//		proxyClassesByJPAEntity.put(JPAPaymentMethod.HOLD, HoldPaymentMethodProxy.class);
//		proxyClassesByJPAEntity.put(JPAPaymentSchedule.MONTHLY, MonthlyPaymentScheduleProxy.class);
//		proxyClassesByJPAEntity.put(JPAPaymentSchedule.WEEKLY, WeeklyPaymentScheduleProxy.class);
//		proxyClassesByJPAEntity.put(JPAPaymentSchedule.BI_WEEKLY, BiWeeklyPaymentScheduleProxy.class);
	}

	public Class<?> getProxyClassFor(Object jpaEntity) {
		Class<?> proxy = getProxyByJPAClass(jpaEntity.getClass());
		if(proxy != null)
			return proxy;
		else
			return assertNotNull(getProxyByJPAObject(jpaEntity));
	}

	private Class<?> getProxyByJPAClass(Class<? extends Object> class1) {
		return proxyClassesByJPAEntityClass.get(class1);
	}

	private Class<?> getProxyByJPAObject(Object jpaEntity) {
		return proxyClassesByJPAEntity.get(jpaEntity);
	}

	private Class<?> assertNotNull(Class<?> proxyClass) {
		if(proxyClass == null)
			throw new NoProxyClassAssignedToJPAClassException();
		return proxyClass;
	}

	public static class NoProxyClassAssignedToJPAClassException extends RuntimeException {}

	
}
