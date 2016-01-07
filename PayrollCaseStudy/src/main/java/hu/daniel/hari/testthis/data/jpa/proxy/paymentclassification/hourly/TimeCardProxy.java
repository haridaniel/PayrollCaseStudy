package hu.daniel.hari.testthis.data.jpa.proxy.paymentclassification.hourly;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;
import hu.daniel.hari.testthis.data.jpa.model.paymentclassification.SalariedJPAPaymentClassification;
import hu.daniel.hari.testthis.data.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.testthis.data.jpa.proxy.Proxy;
import hu.daniel.hari.testthis.data.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPATimeCard.class)
public class TimeCardProxy extends TimeCard implements Proxy<JPATimeCard> {

	private JPATimeCard jpaTimeCard;

	public TimeCardProxy(JPATimeCard jpaTimeCard) {
		this.jpaTimeCard = jpaTimeCard;
	}

	@Override
	public LocalDate getDate() {
		return jpaTimeCard.id.date;
	}
	
	@Override
	public int getWorkingHourQty() {
		return jpaTimeCard.workingHourQty;
	}
	
	@Override
	public void setDate(LocalDate date) {
		jpaTimeCard.id.date = date;
	}
	
	@Override
	public void setWorkingHourQty(int workingHourQty) {
		jpaTimeCard.workingHourQty = workingHourQty;
	}

	@Override
	public JPATimeCard getJPAObject() {
		return jpaTimeCard;
	}
	
}
