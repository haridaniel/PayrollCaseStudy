package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentSchedule;

public class JPAPaymentScheduleFactory {

	public static JPAPaymentSchedule create(PaymentSchedule paymentSchedule) {
		if (paymentSchedule instanceof MontlhyPaymentSchedule) {
			return JPAPaymentSchedule.MONTHLY;
		} else if (paymentSchedule instanceof WeeklyPaymentSchedule) {
			return JPAPaymentSchedule.WEEKLY;
		} else
			throw new NotImplementedException();
	}

}
