package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentSize;

public class SalariedPaymentClassificationProxy extends SalariedPaymentClassification {

	private SalariedJPAPaymentSize salariedJPAPaymentSize;

	public SalariedPaymentClassificationProxy(SalariedJPAPaymentSize jpaPaymentSize) {
		super(0);
		this.salariedJPAPaymentSize = jpaPaymentSize;
	}

	@Override
	public int getMonthlySalary() {
		return salariedJPAPaymentSize.getMonthlySalary();
	}
	
	@Override
	public void setMonthlySalary(int monthlySalary) {
		salariedJPAPaymentSize.setMonthlySalary(monthlySalary);
	}
	
}
