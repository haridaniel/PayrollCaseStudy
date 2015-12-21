package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;

@AutoBindedProxy(SalariedJPAPaymentClassification.class)
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
	public JPAPaymentClassification getJPAObject() {
		return salariedJPAPaymentClassification;
	}

}
