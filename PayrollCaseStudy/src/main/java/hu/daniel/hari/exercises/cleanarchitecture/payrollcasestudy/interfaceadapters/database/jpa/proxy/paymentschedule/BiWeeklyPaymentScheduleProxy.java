package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentschedule.JPABiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.BiWeeklyPaymentSchedule;

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
