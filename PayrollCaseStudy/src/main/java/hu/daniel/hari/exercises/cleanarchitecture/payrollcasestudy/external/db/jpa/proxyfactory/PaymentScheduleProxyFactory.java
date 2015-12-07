package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.MonthlyPaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.PaymentScheduleProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule.WeeklyPaymentScheduleProxy;

public class PaymentScheduleProxyFactory {

	public static PaymentScheduleProxy create(JPAPaymentSchedule jpaPaymentSchedule) {
		switch (jpaPaymentSchedule) {
		case MONTHLY:
			return new MonthlyPaymentScheduleProxy();
		case WEEKLY:
			return new WeeklyPaymentScheduleProxy();
		default:
			throw new NotImplementedException();
		}
	}

}
