package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentSchedule;

public interface PaymentScheduleProxy extends PaymentSchedule {

	JPAPaymentSchedule getJPAPaymentSchedule();
	
}
