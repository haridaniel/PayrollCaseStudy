package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.SalariedJPAPaymentClassification;

public class SalariedPaymentClassificationProxy extends SalariedPaymentClassification implements PaymentClassificationProxy {

	private SalariedJPAPaymentClassification salariedJPAPaymentClassification;

	public SalariedPaymentClassificationProxy(SalariedJPAPaymentClassification jpaPaymentSize) {
		this.salariedJPAPaymentClassification = jpaPaymentSize;
	}

	@Override
	public int getMonthlySalary() {
		return salariedJPAPaymentClassification.getMonthlySalary();
	}
	
	@Override
	public void setMonthlySalary(int monthlySalary) {
		salariedJPAPaymentClassification.setMonthlySalary(monthlySalary);
	}

	@Override
	public JPAPaymentClassification getJPAPaymentClassification() {
		return salariedJPAPaymentClassification;
	}

}
