package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentsize;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentSize;

class AbstractJPAPaymentSizeFactory {

	protected JPAPaymentSize fillForeignKey(JPAPaymentSize jpaPaymentSize, JPAEmployee jpaEmployee) {
		jpaPaymentSize.employeeId = jpaEmployee.id;
		jpaPaymentSize.jpaEmployee = jpaEmployee;
		return jpaPaymentSize;
	}

}