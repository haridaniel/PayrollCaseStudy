package hu.daniel.hari.testthis.data.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.testthis.data.jpa.model.paymentschedule.JPABiWeeklyPaymentSchedule;
import hu.daniel.hari.testthis.data.jpa.proxy.util.autobind.AutoBindedProxy;

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
