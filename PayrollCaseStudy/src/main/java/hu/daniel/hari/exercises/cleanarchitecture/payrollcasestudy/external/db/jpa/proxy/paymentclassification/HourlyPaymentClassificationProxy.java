package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.SalariedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification.hourly.JPATimeCardFactory;

public class HourlyPaymentClassificationProxy extends HourlyPaymentClassification {

	private HourlyJPAPaymentClassification hourlyJPAPaymentClassification;

	public HourlyPaymentClassificationProxy(HourlyJPAPaymentClassification hourlyJPAPaymentClassification) {
		super(-1);
		this.hourlyJPAPaymentClassification = hourlyJPAPaymentClassification;
	}
	
	@Override
	public int getHourlyWage() {
		return hourlyJPAPaymentClassification.getHourlyWage();
	}
	
	@Override
	public void setHourlyWage(int hourlyWage) {
		hourlyJPAPaymentClassification.setHourlyWage(hourlyWage);
	}
	
	@Override
	public void addTimeCard(TimeCard timeCard) {
		hourlyJPAPaymentClassification.addJPATimeCard(
				JPATimeCardFactory.create(timeCard, hourlyJPAPaymentClassification));
	}
	
	@Override
	public TimeCard getTimeCard(LocalDate date) {
		return hourlyJPAPaymentClassification.getTimeCard(date);
	}
	
}
