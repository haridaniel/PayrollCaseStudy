package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentschedule.JPAMonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.MontlhyPaymentSchedule;

@AutoBindedProxy(JPAMonthlyPaymentSchedule.class)
public class MonthlyPaymentScheduleProxy extends MontlhyPaymentSchedule implements PaymentScheduleProxy {

	private JPAMonthlyPaymentSchedule jpaPaymentSchedule;

	public MonthlyPaymentScheduleProxy(JPAMonthlyPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}

	@Override
	public JPAMonthlyPaymentSchedule getJPAObject() {
		return jpaPaymentSchedule;
	}
	
}
