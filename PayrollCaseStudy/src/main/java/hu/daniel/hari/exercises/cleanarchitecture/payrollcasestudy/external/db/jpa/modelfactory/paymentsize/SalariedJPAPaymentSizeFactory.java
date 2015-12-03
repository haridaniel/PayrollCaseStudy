package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentsize;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentSize;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentSize;

class SalariedJPAPaymentSizeFactory extends AbstractJPAPaymentSizeFactory {

	public JPAPaymentSize create(JPAEmployee jpaEmployee, SalariedPaymentClassification salariedPaymentClassification) {
		SalariedJPAPaymentSize salariedJPAPaymentSize = new SalariedJPAPaymentSize();
		salariedJPAPaymentSize.setMonthlySalary(salariedPaymentClassification.getMonthlySalary());
		return fillForeignKey(salariedJPAPaymentSize, jpaEmployee);
	}
	
}
