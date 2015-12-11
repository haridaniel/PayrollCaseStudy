package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

public abstract class Employee {

	public abstract int getId();
	public abstract String getName();
	public abstract String getAddress();
	public abstract PaymentSchedule getPaymentSchedule();
	public abstract PaymentClassification getPaymentClassification();
	public abstract PaymentMethod getPaymentMethod();
	public abstract Affiliation getAffiliation();

	public abstract void setId(int id);
	public abstract void setName(String name);
	public abstract void setAddress(String address);
	public abstract void setPaymentSchedule(PaymentSchedule paymentSchedule);
	public abstract void setPaymentClassification(PaymentClassification paymentClassification);
	public abstract void setPaymentMethod(PaymentMethod paymentMethod);
	public abstract void setAffiliation(Affiliation affiliation);

	public boolean isPayDate(LocalDate date) {
		return getPaymentSchedule().isPayDate(date);
	}

	public PayCheck createPayCheck(LocalDate payDate) {
		DateInterval payInterval = getPaymentSchedule().getPayInterval(payDate);
		int grossAmount = getPaymentClassification().calculateAmount(payInterval);
		int deductionsAmount = getAffiliation().calculateDeductionsAmount(payInterval);
		return new PayCheck(grossAmount, deductionsAmount);
	}

}
