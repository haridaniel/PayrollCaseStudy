package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentSize;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentSize;

public class HourlyPaymentClassificationProxy extends HourlyPaymentClassification {

	private HourlyJPAPaymentSize hourlyJPAPaymentSize;

	public HourlyPaymentClassificationProxy(HourlyJPAPaymentSize hourlyJPAPaymentSize) {
		super(-1);
		this.hourlyJPAPaymentSize = hourlyJPAPaymentSize;
	}
	
	@Override
	public int getHourlyWage() {
		return hourlyJPAPaymentSize.getHourlyWage();
	}
	
	@Override
	public void setHourlyWage(int hourlyWage) {
		hourlyJPAPaymentSize.setHourlyWage(hourlyWage);
	}
	
}
