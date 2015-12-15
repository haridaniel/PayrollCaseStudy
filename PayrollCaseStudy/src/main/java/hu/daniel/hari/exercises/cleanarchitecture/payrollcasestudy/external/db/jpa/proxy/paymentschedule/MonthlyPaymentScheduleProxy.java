package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAPaymentSchedule;

public class MonthlyPaymentScheduleProxy extends MontlhyPaymentSchedule implements PaymentScheduleProxy {

	private JPAPaymentSchedule jpaPaymentSchedule;

	public MonthlyPaymentScheduleProxy(JPAPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}
	
	@Override
	public JPAPaymentSchedule getJPAPaymentSchedule() {
		return jpaPaymentSchedule;
	}

	@Override
	public JPAPaymentSchedule getJPAObject() {
		return jpaPaymentSchedule;
	}

	@Override
	public Class<JPAPaymentSchedule> getJPAClass() {
		return JPAPaymentSchedule.class;
	}


}
