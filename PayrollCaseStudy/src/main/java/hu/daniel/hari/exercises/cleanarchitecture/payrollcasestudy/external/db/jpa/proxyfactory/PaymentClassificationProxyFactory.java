package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy.HourlyPaymentClassificationProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;

public class PaymentClassificationProxyFactory {
	
	@Inject
	private HourlyPaymentClassificationProxyFactory hourlyPaymentClassificationProxyFactory;
	
	public PaymentClassification create(JPAPaymentClassification jpaPaymentClassification) {
		if (jpaPaymentClassification instanceof SalariedJPAPaymentClassification)
			return new SalariedPaymentClassificationProxy((SalariedJPAPaymentClassification) jpaPaymentClassification);
		else if (jpaPaymentClassification instanceof HourlyJPAPaymentClassification)
			return hourlyPaymentClassificationProxyFactory.create((HourlyJPAPaymentClassification) jpaPaymentClassification);
		else
			throw new NotImplementedException();
	}

	
	
}
