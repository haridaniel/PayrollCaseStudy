package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.Proxy;

public interface PaymentScheduleProxy extends Proxy<JPAPaymentSchedule>{

	JPAPaymentSchedule getJPAPaymentSchedule();
	
}
