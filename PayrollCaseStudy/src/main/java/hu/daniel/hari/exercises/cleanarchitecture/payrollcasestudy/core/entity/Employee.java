package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity;

import java.time.LocalDate;

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

	public abstract void setId(int id);
	public abstract void setName(String name);
	public abstract void setAddress(String address);
	public abstract void setPaymentSchedule(PaymentSchedule paymentSchedule);
	public abstract void setPaymentClassification(PaymentClassification paymentClassification);
	public abstract void setPaymentMethod(PaymentMethod paymentMethod);

	public boolean isPayDate(LocalDate date) {
		return getPaymentSchedule().isPayday(date);
	}

	public int calculatePayAmount(LocalDate payDate) {
		DateInterval payInterval = getPaymentSchedule().getPayInterval(payDate);
		return getPaymentClassification().calculateAmount(payInterval);
	}

}
