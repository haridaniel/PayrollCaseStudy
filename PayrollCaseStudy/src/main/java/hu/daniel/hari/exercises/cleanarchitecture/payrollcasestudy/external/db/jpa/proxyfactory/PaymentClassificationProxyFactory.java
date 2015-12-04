package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.SalariedPaymentClassificationProxy;

public class PaymentClassificationProxyFactory {

	public static PaymentClassification create(JPAPaymentClassification jpaPaymentSize) {
		if(jpaPaymentSize instanceof SalariedJPAPaymentClassification) {
			return new SalariedPaymentClassificationProxy((SalariedJPAPaymentClassification) jpaPaymentSize);
		} else if (jpaPaymentSize instanceof HourlyJPAPaymentClassification) {
			return new HourlyPaymentClassificationProxy((HourlyJPAPaymentClassification) jpaPaymentSize);
		}
		else
			throw new NotImplementedException();
	}

}
