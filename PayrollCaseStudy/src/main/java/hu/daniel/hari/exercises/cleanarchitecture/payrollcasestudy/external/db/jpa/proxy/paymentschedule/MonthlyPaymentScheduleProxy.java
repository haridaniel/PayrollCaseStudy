package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentSchedule;

public class MonthlyPaymentScheduleProxy extends MontlhyPaymentSchedule implements PaymentScheduleProxy {

	@Override
	public JPAPaymentSchedule getJPAPaymentSchedule() {
		return JPAPaymentSchedule.MONTHLY;
	}

}
