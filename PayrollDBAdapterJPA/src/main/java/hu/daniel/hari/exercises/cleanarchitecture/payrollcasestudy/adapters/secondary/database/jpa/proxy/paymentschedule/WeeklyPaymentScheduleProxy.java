package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentschedule.JPAWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;

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
