package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentSize;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentSize;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentSize;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.HourlyPaymentClassificationProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.SalariedPaymentClassificationProxy;

public class PaymentClassificationProxyFactory {

	public static PaymentClassification create(JPAPaymentSize jpaPaymentSize) {
		if(jpaPaymentSize instanceof SalariedJPAPaymentSize) {
			return new SalariedPaymentClassificationProxy((SalariedJPAPaymentSize) jpaPaymentSize);
		} else if (jpaPaymentSize instanceof HourlyJPAPaymentSize) {
			return new HourlyPaymentClassificationProxy((HourlyJPAPaymentSize) jpaPaymentSize);
		}
		else
			throw new NotImplementedException();
	}

}
