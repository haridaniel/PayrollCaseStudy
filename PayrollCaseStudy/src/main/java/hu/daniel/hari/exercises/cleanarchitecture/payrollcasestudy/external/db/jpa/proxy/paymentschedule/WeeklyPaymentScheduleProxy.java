package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentschedule.JPAPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentschedule.JPAWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPAWeeklyPaymentSchedule.class)
public class WeeklyPaymentScheduleProxy extends WeeklyPaymentSchedule implements PaymentScheduleProxy {
	
	private JPAWeeklyPaymentSchedule jpaPaymentSchedule;

	public WeeklyPaymentScheduleProxy(JPAWeeklyPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}
	
	@Override
	public JPAWeeklyPaymentSchedule getJPAObject() {
		return jpaPaymentSchedule;
	}


}
