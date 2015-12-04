package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentClassification;

class SalariedJPAPaymentClassificationFactory extends AbstractJPAPaymentClassificationFactory {

	public JPAPaymentClassification create(JPAEmployee jpaEmployee, SalariedPaymentClassification salariedPaymentClassification) {
		SalariedJPAPaymentClassification salariedJPAPaymentClassification = new SalariedJPAPaymentClassification();
		salariedJPAPaymentClassification.setMonthlySalary(salariedPaymentClassification.getMonthlySalary());
		return fillForeignKey(salariedJPAPaymentClassification, jpaEmployee);
	}
	
}
