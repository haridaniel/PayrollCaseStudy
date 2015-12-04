package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentClassification;

class AbstractJPAPaymentClassificationFactory {

	protected JPAPaymentClassification fillForeignKey(JPAPaymentClassification jpaPaymentSize, JPAEmployee jpaEmployee) {
		jpaPaymentSize.employeeId = jpaEmployee.id;
		jpaPaymentSize.jpaEmployee = jpaEmployee;
		return jpaPaymentSize;
	}

}