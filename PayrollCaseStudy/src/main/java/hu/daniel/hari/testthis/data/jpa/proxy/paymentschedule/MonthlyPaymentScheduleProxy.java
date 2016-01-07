package hu.daniel.hari.testthis.data.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.MonthlyPaymentSchedule;
import hu.daniel.hari.testthis.data.jpa.model.paymentschedule.JPAMonthlyPaymentSchedule;
import hu.daniel.hari.testthis.data.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPAMonthlyPaymentSchedule.class)
public class MonthlyPaymentScheduleProxy extends MonthlyPaymentSchedule implements PaymentScheduleProxy {

	private JPAMonthlyPaymentSchedule jpaPaymentSchedule;

	public MonthlyPaymentScheduleProxy(JPAMonthlyPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}

	@Override
	public JPAMonthlyPaymentSchedule getJPAObject() {
		return jpaPaymentSchedule;
	}
	
}
