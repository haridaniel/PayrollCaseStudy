package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification;


import java.util.HashSet;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPATimeCard;

class HourlyJPAPaymentClassificationFactory extends AbstractJPAPaymentClassificationFactory {

	public JPAPaymentClassification create(JPAEmployee jpaEmployee, HourlyPaymentClassification hourlyPaymentClassification) {
		HourlyJPAPaymentClassification hourlyJPAPaymentClassification = new HourlyJPAPaymentClassification();
		hourlyJPAPaymentClassification.setHourlyWage(hourlyPaymentClassification.getHourlyWage());
		return fillForeignKey(hourlyJPAPaymentClassification, jpaEmployee);
	}

}
