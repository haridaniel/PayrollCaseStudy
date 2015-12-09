package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAPaymentSchedule;

public interface PaymentScheduleProxy {

	JPAPaymentSchedule getJPAPaymentSchedule();
	
}
