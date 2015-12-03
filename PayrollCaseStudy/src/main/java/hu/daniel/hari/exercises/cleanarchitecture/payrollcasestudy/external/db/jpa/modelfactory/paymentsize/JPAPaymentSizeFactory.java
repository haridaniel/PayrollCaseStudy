package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentsize;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentSize;

public abstract class JPAPaymentSizeFactory {

	public static JPAPaymentSize create(JPAEmployee jpaEmployee, PaymentClassification paymentClassification) {
		if(paymentClassification instanceof SalariedPaymentClassification) {
			return new SalariedJPAPaymentSizeFactory().create(jpaEmployee, (SalariedPaymentClassification) paymentClassification);
		} else 
		if(paymentClassification instanceof HourlyPaymentClassification) {
			return new HourlyJPAPaymentSizeFactory().create(jpaEmployee, (HourlyPaymentClassification) paymentClassification);
		} else 
			throw new NotImplementedException();
	}
}
