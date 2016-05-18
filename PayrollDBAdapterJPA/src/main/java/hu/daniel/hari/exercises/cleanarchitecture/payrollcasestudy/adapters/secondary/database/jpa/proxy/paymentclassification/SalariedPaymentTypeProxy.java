package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.JPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.SalariedJPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalariedPaymentType;

@AutoBindedProxy(SalariedJPAPaymentType.class)
public class SalariedPaymentTypeProxy extends SalariedPaymentType implements PaymentTypeProxy {

	private SalariedJPAPaymentType salariedJPAPaymentType;

	public SalariedPaymentTypeProxy(SalariedJPAPaymentType jpaPaymentSize) {
		this.salariedJPAPaymentType = jpaPaymentSize;
	}

	@Override
	public int getMonthlySalary() {
		return salariedJPAPaymentType.getMonthlySalary();
	}
	
	@Override
	public void setMonthlySalary(int monthlySalary) {
		salariedJPAPaymentType.setMonthlySalary(monthlySalary);
	}

	@Override
	public JPAPaymentType getJPAObject() {
		return salariedJPAPaymentType;
	}

}
