package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentschedule.JPABiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.factory.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.BiWeeklyPaymentSchedule;

@AutoBindedProxy(JPABiWeeklyPaymentSchedule.class)
public class BiWeeklyPaymentScheduleProxy extends BiWeeklyPaymentSchedule implements PaymentScheduleProxy {

	private JPABiWeeklyPaymentSchedule jpaPaymentSchedule;

	public BiWeeklyPaymentScheduleProxy(JPABiWeeklyPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}
	
	@Override
	public JPABiWeeklyPaymentSchedule getJPAObject() {
		return jpaPaymentSchedule;
	}

}
