package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentschedule.JPAMonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.MonthlyPaymentSchedule;

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
