package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentsize;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentSize;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentSize;

class HourlyJPAPaymentSizeFactory extends AbstractJPAPaymentSizeFactory {

	public JPAPaymentSize create(JPAEmployee jpaEmployee, HourlyPaymentClassification hourlyPaymentClassification) {
		HourlyJPAPaymentSize hourlyJPAPaymentSize = new HourlyJPAPaymentSize();
		hourlyJPAPaymentSize.setHourlyWage(hourlyPaymentClassification.getHourlyWage());
		return fillForeignKey(hourlyJPAPaymentSize, jpaEmployee);
	}

	
}
