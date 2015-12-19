package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.model.paymentschedule.JPABiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.proxy.util.autobind.AutoBindedProxy;

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
