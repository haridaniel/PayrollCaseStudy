package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentClassification;

public abstract class JPAPaymentClassificationFactory {

	public static JPAPaymentClassification create(JPAEmployee jpaEmployee, PaymentClassification paymentClassification) {
		if(paymentClassification instanceof SalariedPaymentClassification) {
			return new SalariedJPAPaymentClassificationFactory().create(jpaEmployee, (SalariedPaymentClassification) paymentClassification);
		} else 
		if(paymentClassification instanceof HourlyPaymentClassification) {
			return new HourlyJPAPaymentClassificationFactory().create(jpaEmployee, (HourlyPaymentClassification) paymentClassification);
		} else 
			throw new NotImplementedException();
	}
}
