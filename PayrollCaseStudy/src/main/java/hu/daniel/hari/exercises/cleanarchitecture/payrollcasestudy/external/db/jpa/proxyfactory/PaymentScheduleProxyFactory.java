package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentSchedule;

public class PaymentScheduleProxyFactory {

	public static PaymentSchedule create(JPAPaymentSchedule jpaPaymentSchedule) {
		if (jpaPaymentSchedule == JPAPaymentSchedule.MONTHLY)
			return new MontlhyPaymentSchedule();
		else if (jpaPaymentSchedule == JPAPaymentSchedule.WEEKLY)
			return new WeeklyPaymentSchedule();
		else
			throw new NotImplementedException();
	}

}
