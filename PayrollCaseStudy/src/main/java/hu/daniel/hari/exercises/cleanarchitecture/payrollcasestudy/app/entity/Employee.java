package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;

public abstract class Employee {

	public abstract int getId();
	public abstract String getName();
	public abstract String getAddress();
	public abstract PaymentSchedule getPaymentSchedule();
	public abstract PaymentType getPaymentType();
	public abstract PaymentMethod getPaymentMethod();
	public abstract Affiliation getAffiliation();

	public abstract void setId(int id);
	public abstract void setName(String name);
	public abstract void setAddress(String address);
	public abstract void setPaymentSchedule(PaymentSchedule paymentSchedule);
	public abstract void setPaymentType(PaymentType paymentType);
	public abstract void setPaymentMethod(PaymentMethod paymentMethod);
	public abstract void setAffiliation(Affiliation affiliation);

	public boolean isPayDate(LocalDate date) {
		return getPaymentSchedule().isPayDate(date);
	}

	public PayCheck createPayCheck(LocalDate payDate) {
		
		DateInterval payInterval = getPaymentSchedule().getPayInterval(payDate);
		int grossAmount = getPaymentType().calculateAmount(payInterval);
		int deductionsAmount = getAffiliation().calculateDeductionsAmount(payInterval);
		int netAmount = grossAmount - deductionsAmount;
		return new PayCheck(payDate, getId(), grossAmount, deductionsAmount, netAmount);
	}
	
	public static interface EmployeeFactory {
		Employee employee();
	}

}
