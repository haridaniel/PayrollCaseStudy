package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPANoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.NoAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember.ServiceChargeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember.UnionMemberAffiliationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.CommissionedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.commissioned.SalesReceiptProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.hourly.TimeCardProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.HoldPaymentMethodProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentmethod.PaymentMethodProxy;
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
		
//		proxyClassesByJPAEntityClass.put(.class, .class);
		
	}
	
	private Map<Object, Class<?>> proxyClassesByJPAEntity = new HashMap<>();
	{
		proxyClassesByJPAEntity.put(JPAPaymentMethod.HOLD, HoldPaymentMethodProxy.class);
		proxyClassesByJPAEntity.put(JPAPaymentSchedule.MONTHLY, MonthlyPaymentScheduleProxy.class);
		proxyClassesByJPAEntity.put(JPAPaymentSchedule.WEEKLY, WeeklyPaymentScheduleProxy.class);
		proxyClassesByJPAEntity.put(JPAPaymentSchedule.BI_WEEKLY, BiWeeklyPaymentScheduleProxy.class);
	}

	public Class<?> getProxyClassFor(Object jpaEntity) {
		Class<?> proxyClassByJPAEntityClass = proxyClassesByJPAEntityClass.get(jpaEntity.getClass());
		if(proxyClassByJPAEntityClass != null)
			return proxyClassByJPAEntityClass;
		Class<?> proxyClassByJPAEntity = proxyClassesByJPAEntity.get(jpaEntity);
		return assertNotNull(proxyClassByJPAEntity);
	}

	private Class<?> assertNotNull(Class<?> proxyClass) {
		if(proxyClass == null)
			throw new NoProxyClassAssignedToJPAClassException();
		return proxyClass;
	}

	public static class NoProxyClassAssignedToJPAClassException extends RuntimeException {}

	
}
