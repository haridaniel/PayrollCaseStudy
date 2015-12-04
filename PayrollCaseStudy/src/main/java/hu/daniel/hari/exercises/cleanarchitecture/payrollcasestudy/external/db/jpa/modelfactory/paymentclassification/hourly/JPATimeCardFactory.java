package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentclassification.hourly;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPATimeCard;

public class JPATimeCardFactory {

	public static JPATimeCard create(TimeCard timeCard, HourlyJPAPaymentClassification hourlyJPAPaymentClassification) {
		JPATimeCard jpaTimeCard = new JPATimeCard();
		jpaTimeCard.id = new JPATimeCard.ID(hourlyJPAPaymentClassification.employeeId, timeCard.date);
		jpaTimeCard.hourlyJPAPaymentClassification = hourlyJPAPaymentClassification;
		jpaTimeCard.workingHourQty = timeCard.workingHourQty;
		return jpaTimeCard;
	}

}
